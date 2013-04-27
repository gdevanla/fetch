package MyTests.bluej.utility.filter;

import java.io.File;
import java.io.IOException;

import MyTests.bluej.Mocks;
import bluej.utility.filefilter.DirectoryFilter;
import bluej.utility.filefilter.JavaClassFilter;
import bluej.utility.filefilter.JavaSourceFilter;
import bluej.utility.filefilter.SubPackageFilter;

/**
 * Testing the bluej.utility.filefilter.DirectoryFilter.java,
 * bluej.utility.filefilter.JavaClassFilter.java,
 * bluej.utility.filefilter.JavaSourceFilter.java and
 * bluej.utility.filefilter.SubPackageFilter.java
 * 
 * @author Nikhat Farha
 *
 */

public class Test17 extends Mocks {
	
	private static String currentDir;
	private static String projectName;
	
	protected void setUp() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		initMocks();
		currentDir = System.getProperty("user.dir");
		projectName = currentDir + File.separator + "newProject17";
	}
	protected void tearDown() {
		
	}
	public void testDirectoryFilter() {
		DirectoryFilter directoryFilter = new DirectoryFilter();
		File projectDirFile = new File(projectName);
		if(!projectDirFile.exists())
			projectDirFile.mkdir();
		assertTrue(directoryFilter.accept(projectDirFile));
	}
	
	public void testJavaClassFilter() throws IOException {
		JavaClassFilter javaClassFilter = new JavaClassFilter();
		String javaClassFileName = projectName + File.separator + "Hello.class";
		File javaClassFile = new File(javaClassFileName);
		if(!javaClassFile.exists())
			javaClassFile.createNewFile();
		assertTrue(javaClassFilter.accept(javaClassFile));
	}
	
	public void testJavaSourceFilter() throws IOException {
		JavaSourceFilter javaSourceFilter = new JavaSourceFilter();
		String javaSourceFileName = projectName + File.separator + "Hello.java";
		File javaSourceFile = new File(javaSourceFileName);
		if(!javaSourceFile.exists())
			javaSourceFile.createNewFile();
		assertTrue(javaSourceFilter.accept(javaSourceFile));
	}
	
	public void testSubPackageFilter() throws IOException {
		SubPackageFilter subPackageFilter = new SubPackageFilter();
		String subPackageName = projectName + File.separator + "subPackage.bluej";
		File subPackage = new File(subPackageName);
		if(!subPackage.exists())
			subPackage.mkdir();
		String bluejPackageName = subPackageName + File.separator + "package.bluej";
		File bluejPackage = new File(bluejPackageName);
		if(!bluejPackage.exists())
			bluejPackage.createNewFile();
		assertTrue(subPackageFilter.accept(subPackage));
	}
}
