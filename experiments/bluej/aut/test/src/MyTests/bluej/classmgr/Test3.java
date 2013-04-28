package MyTests.bluej.classmgr;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import bluej.classmgr.BPClassLoader;

import junit.framework.TestCase;

/**
 * Testing the bluej.classmgr.BPClassLoader.java class
 * 
 * @author Nikhat Farha
 *
 */


public class Test3 extends TestCase{

	URL[] urls = new URL[1];
	
	protected void setUp() throws MalformedURLException {
		 String osname = System.getProperty("os.name", "");
		 File javaHomeDir = new File(System.getProperty("java.home"));
		 URL url = null;
			
		 if(osname.startsWith("Mac"))     // we know it does not exist on a Mac...
			 url =  null;
		 else {
			 File toolsFile = new File(javaHomeDir, "lib" + File.separator + "tools.jar");
			 if (toolsFile.canRead())
				 url =  toolsFile.toURI().toURL();
			 else {
				 File parentDir = javaHomeDir.getParentFile();
				 toolsFile = new File(parentDir, "lib" + File.separator + "tools.jar");
				 if (toolsFile.canRead())
					 url =  toolsFile.toURI().toURL();
				 else {
					 // on other systems where we don't find it, we just warn. We don't expect it
					 // to happen, but you never know...
					 System.err.println("class Boot: tools.jar not found. Potential problem for execution.");
					 url = null;
				 }
			 }
		 }
		 urls[0] = url;
		
    }
    
	 protected void tearDown() {
	        // nothing to do
	 }
	 public void testClassLoader() {
		 try {
			 assertNotNull(urls);
			BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
			BPClassLoader bploader1 = new BPClassLoader(urls,new URLClassLoader(urls),true);
			assertFalse(bploader.loadsForJavaMEproject());
			assertTrue(bploader1.loadsForJavaMEproject());
			
		 }
		 catch(Exception e) {
			 fail();
		 }
	 }
	 
	 public void testSameURLs() throws MalformedURLException {
		 String osname = System.getProperty("os.name", "");
		 File javaHomeDir = new File(System.getProperty("java.home"));
		 URL[] newUrls = new URL[1];
		 URL url = null;
		 if(!osname.startsWith("Mac"))    { // we know it does not exist on a Mac...
			 File toolsFile = new File(javaHomeDir, "lib"+ File.separator + "tools.jar");
			 if (toolsFile.canRead())
				 url =  toolsFile.toURI().toURL();
			 else {
				 File parentDir = javaHomeDir.getParentFile();
				 toolsFile = new File(parentDir, "lib" + File.separator + "tools.jar");
				 if (toolsFile.canRead())
					 url =  toolsFile.toURI().toURL();
				 else 
					 url = null;
			 }
		 }
		 assertNotNull(url);
		 newUrls[0] = url;
		 assertNotNull(urls);
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 assertTrue(bploader.sameUrls(newUrls));
	 }
	 
	 public void testGetClassPathAsString() {
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 String javaHome = System.getProperty("java.home");
		
		 //excluding the /jre from the end.
		 javaHome = javaHome.substring(0,javaHome.length()-4);
		 String classpath = javaHome +  File.separator + "lib" + File.separator + "tools.jar";
		 assertEquals(classpath, bploader.getClassPathAsString());
	 }
	 
	 public void testToString() {
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 String javaHome = System.getProperty("java.home");
		
		 //excluding the /jre from the end.
		 javaHome = javaHome.substring(0,javaHome.length()-4);
		 String classpath = javaHome +  File.separator + "lib" + File.separator + "tools.jar";
		 String toCompare = "BPClassLoader path=" + classpath;
		 assertEquals(toCompare, bploader.toString());
	 }
	 
	 public void testGetJavaMElibsAsPath() {
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 ArrayList<URL> urlList = new ArrayList<URL>();
		 ArrayList<URL> urlList1 = new ArrayList<URL>();
		 urlList.add(urls[0]);
		 bploader.setJavaMEcoreLibs(urlList);
		 bploader.setJavaMEoptLibs(urlList1);
		 
		 String javaHome = System.getProperty("java.home");
		//excluding the /jre from the end.
		 javaHome = javaHome.substring(0,javaHome.length()-4);
		 String classpath = javaHome +  File.separator + "lib" + File.separator + "tools.jar";
		 assertEquals(classpath,bploader.getJavaMElibsAsPath());
		 
	 }
	 
	 public void testGetClassPathAsFiles() throws MalformedURLException {
		 String osname = System.getProperty("os.name", "");
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 File javaHomeDir = new File(System.getProperty("java.home"));
		 File[] files = new File[1];
		 File toolsFile = null;
		 if(!osname.startsWith("Mac"))    { // we know it does not exist on a Mac...
			 toolsFile = new File(javaHomeDir, "lib" + File.separator + "tools.jar");
			 if (!toolsFile.canRead()) {
				 File parentDir = javaHomeDir.getParentFile();
				 toolsFile = new File(parentDir, "lib" + File.separator + "tools.jar");
			 }
		 }
		 files[0] = toolsFile;
		 assertEquals(files.length,bploader.getClassPathAsFiles().length);
		 assertEquals(0,files[0].compareTo(bploader.getClassPathAsFiles()[0]));
	 }
	 
	 
	 public void testGetJavaMElibsAsFiles() throws MalformedURLException {
		 String osname = System.getProperty("os.name", "");
		 BPClassLoader bploader = new BPClassLoader(urls,new URLClassLoader(urls));
		 
		 ArrayList<URL> urlList = new ArrayList<URL>();
		 ArrayList<URL> urlList1 = new ArrayList<URL>();
		 urlList.add(urls[0]);
		 bploader.setJavaMEcoreLibs(urlList);
		 bploader.setJavaMEoptLibs(urlList1);
		 
		 File javaHomeDir = new File(System.getProperty("java.home"));
		 File[] files = new File[1];
		 File toolsFile = null;
		 if(!osname.startsWith("Mac"))    { // we know it does not exist on a Mac...
			 toolsFile = new File(javaHomeDir, "lib" + File.separator + "tools.jar");
			 if (!toolsFile.canRead()) {
				 File parentDir = javaHomeDir.getParentFile();
				 toolsFile = new File(parentDir, "lib" + File.separator + "tools.jar");
			 }
		 }
		 files[0] = toolsFile;
		 
		 assertEquals(files.length,bploader.getJavaMElibsAsFiles().length);
		 assertEquals(0,files[0].compareTo(bploader.getJavaMElibsAsFiles()[0]));
	 }
}
	
