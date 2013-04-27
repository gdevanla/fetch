package MyTests.bluej.pkgmgr;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import bluej.Config;
import bluej.pkgmgr.BlueJPackageFile;
import bluej.pkgmgr.PackageFileFactory;

import junit.framework.TestCase;

/**
 * Testing bluej.pkgmgr.BlueJPackageFile.java and
 * bluej.pkgmgr.PackageFileFactory.java with all possible exceptions thrown.
 * Assumes that the Config.isGreenfoot() is false.
 * 
 * @author Nikhat Farha
 *
 */

public class Test15 extends TestCase {
	
	private static File dir;
	private static BlueJPackageFile pkg;
	private static String currentDir;
	private static String projectName;
	
	protected void setUp() {
		currentDir =  System.getProperty("user.dir");
		projectName = currentDir + File.separator + "newProject15";
	}
	protected void tearDown() {
		
	}
	
	public void testPackageCreate() {
		
		String pkgfileName = "package.bluej";
		String oldPkgfileName = "bluej.pkg";
		
		dir = new File(projectName);
		dir.mkdir();
		
		pkg = (BlueJPackageFile) PackageFileFactory.getPackageFile(dir);
		try {
			File pkgFile = new File(dir, pkgfileName);
	        File oldPkgFile = new File(dir, oldPkgfileName);
	        if (pkgFile.exists() && !oldPkgFile.exists()) {
	        	assertFalse(pkg.create());
	        }
	        if (!pkgFile.exists()) {
	        	assertTrue(pkg.create());
	        }
		}
		catch(IOException e) {
			fail();
		}
	}
	
	public void testExists() {
		 //Checking with an existing directory
		 assertTrue(BlueJPackageFile.exists(dir));
		 //Checking with a non-existing directory
		 String projectName = currentDir + File.separator + "newProject15_1";
		 File dir1 = new File(projectName);
		 assertFalse(BlueJPackageFile.exists(dir1));
	}

	
	public void testIsOldPackageFileName() {
		String pkgfileName = "package.bluej";
		String oldPkgfileName = "bluej.pkg";
		assertFalse(BlueJPackageFile.isOldPackageFileName(pkgfileName));
		assertTrue(BlueJPackageFile.isOldPackageFileName(oldPkgfileName));
	}
	
	public void testIsPackageFileName() {
		String pkgfileName = "package.bluej";
		String oldPkgfileName = "bluej.pkg";
		String nonExistentPackageName = "Nikhat.pkg";
		
		assertTrue(BlueJPackageFile.isPackageFileName(pkgfileName));
		assertTrue(BlueJPackageFile.isPackageFileName(oldPkgfileName));
		assertFalse(BlueJPackageFile.isPackageFileName(nonExistentPackageName));
	}
	
	public void testSave() {
		 Properties props = new Properties();
		 props.put("Name1", "Value1");
		 props.put("Name2", "Value2");
		 props.put("Name3", "Value3");
		 props.put("Name4", "Value4");
		 try {
			pkg.save(props);
		 } catch (IOException e) {
			fail();
		 }
		 String pkgfileName = "package.bluej";
		 String fullPkgFileName = projectName + File.separator + pkgfileName;
		 File pkgFile = new File(projectName,pkgfileName);
		 pkgFile.setWritable(false);
		 try {
			 pkg.save(props);
			 fail("IOException should have been thrown!");
		 } catch (IOException e) {
			 assertEquals("BlueJ package file not writable: "+fullPkgFileName,e.getMessage());
		 }
		 pkgFile.delete();
		 try {
			 //A new bluej file will be created for saving.
			 pkg.save(props);
		 } catch (IOException e) {
				fail();
		 }
	}
	
	public void testLoad() {
		 Properties props = new Properties();
		 try {
			pkg.load(props);
			assertEquals("Value1",props.getProperty("Name1"));
			assertEquals("Value2",props.getProperty("Name2"));
			assertEquals("Value3",props.getProperty("Name3"));
			assertEquals("Value4",props.getProperty("Name4"));
		 } catch (IOException e) {
		//	e.printStackTrace();
			fail();
		 }
		 String pkgfileName = "package.bluej";
		 String newFileName = "package.bluej1";
		 File pkgFile = new File(projectName,pkgfileName);
		 File newPkgFile = new File(projectName,newFileName); 
		 if(newPkgFile.exists())
			 newPkgFile.delete();
		 pkgFile.renameTo(newPkgFile);
		 props.clear();
		 try {
			 //A new bluej file will be created for saving.
			 pkg.load(props);
			 fail("IOException should have been thrown!");
		 } catch (IOException e) {
				assertEquals("Can't read from package file(s) in: BlueJ package file in: "+projectName,e.getMessage());
		 }
	}
	
	public void testToString() {
		assertEquals("BlueJ package file in: "+projectName,pkg.toString());
	}
	
	
}
