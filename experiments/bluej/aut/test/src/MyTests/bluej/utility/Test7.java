package MyTests.bluej.utility;

import java.io.File;

import bluej.utility.JavaNames;
import junit.framework.TestCase;

/**
 * This is testing the utility class JavaNames.java which will check which 
 * keywords, naming conventions are permitted and which are not.
 * The different manipulative methods of this class are tested out.
 * 
 * @author Nikhat Farha
 *
 */
public class Test7 extends TestCase{
	protected void setUp() {
		
	}
	
	protected void tearDown() {
		
	}
	
	public void testIsIdentifier() {
		assertTrue(JavaNames.isIdentifier("hello"));
		assertFalse(JavaNames.isIdentifier("new"));
		assertFalse(JavaNames.isIdentifier("throws"));
		assertFalse(JavaNames.isIdentifier("final"));
		assertFalse(JavaNames.isIdentifier("static"));
		assertFalse(JavaNames.isIdentifier("class"));
	}
	
	public void testIsQualifiedIdentifier() {
		assertTrue(JavaNames.isQualifiedIdentifier(""));
		assertTrue(JavaNames.isQualifiedIdentifier("java.sun"));
		assertTrue(JavaNames.isQualifiedIdentifier(".java.sun"));
		assertTrue(JavaNames.isQualifiedIdentifier("java.sun."));
		assertFalse(JavaNames.isQualifiedIdentifier("new"));
		assertFalse(JavaNames.isQualifiedIdentifier("final"));
		assertFalse(JavaNames.isQualifiedIdentifier("static"));
		assertFalse(JavaNames.isQualifiedIdentifier("class"));
		assertTrue(JavaNames.isQualifiedIdentifier("hello"));
	}
	
	public void testStripPrefix() {
		assertEquals("Main",JavaNames.stripPrefix("bluej.test.Main"));
		assertEquals("Main",JavaNames.stripPrefix("Main"));
		assertNull(JavaNames.stripPrefix(null));
	}
	
	public void testStripSuffix() {
		assertEquals("bluej.test.Main",JavaNames.stripSuffix("bluej.test.Main.java",".java"));
		assertEquals("Main",JavaNames.stripSuffix("Main.class" , ".class"));
		try {
			assertNull(JavaNames.stripSuffix(null,".class"));
			fail("Exception is expected");
		}
		catch(NullPointerException e) {
			//success
		}
	}
	
	public void testGetBase() {
		assertEquals("Main",JavaNames.getBase("bluej.test.Main"));
		assertEquals("Main",JavaNames.getBase("Main"));
		assertEquals("",JavaNames.getBase(""));
		try {
			assertNull(JavaNames.getBase(null));
			fail("Exception is expected");
		}
		catch(NullPointerException e) {
			//success
		}
	}
	
	public void testGetPrefix() {
		assertEquals("bluej.test",JavaNames.getPrefix("bluej.test.Main"));
		assertEquals("",JavaNames.getPrefix("Main"));
		assertEquals("",JavaNames.getPrefix(""));
		try {
			assertNull(JavaNames.getPrefix(null));
			fail("Exception is expected");
		}
		catch(NullPointerException e) {
			//success
		}
	}
	
	public void testConvertFileToQualifiedName() {
		String currentDir =  System.getProperty("user.dir");
		String fileName = "newFile";
		File fileDir = new File(currentDir);
		File file = new File(currentDir + File.separator + fileName);
		assertEquals(fileName, JavaNames.convertFileToQualifiedName(fileDir, file));
	}
	
	public void testConvertQualifiedNameToFile() {
		String currentDir =  System.getProperty("user.dir");
		String fileName = "newFile.txt";
		File fileDir = new File(currentDir);
		String expectedFileName = "newFile"+ File.separator + "txt";
		File file = new File(currentDir + File.separator + expectedFileName);
		assertEquals(file.getAbsolutePath(), JavaNames.convertQualifiedNameToFile(fileName, fileDir).getAbsolutePath());
	}
	
	public void testTypeNames() {
		String className = "[LString.class;";
		assertEquals("String.class[]",JavaNames.typeName(className));
		className = "[BString";
		assertEquals("byte[]",JavaNames.typeName(className));
		className = "[CString";
		assertEquals("char[]",JavaNames.typeName(className));
		className = "[DString";
		assertEquals("double[]",JavaNames.typeName(className));
		className = "[FString";
		assertEquals("float[]",JavaNames.typeName(className));
		className = "[IString";
		assertEquals("int[]",JavaNames.typeName(className));
		className = "[JString";
		assertEquals("long[]",JavaNames.typeName(className));
		className = "[SString";
		assertEquals("short[]",JavaNames.typeName(className));
		className = "[ZString";
		assertEquals("boolean[]",JavaNames.typeName(className));
		
		
	}
	
	public void testCombineNames() {
		String first = "firstName";
		String second = "secondName";
		String combined = first + "." + second;
		assertEquals(combined,JavaNames.combineNames(first, second));
		
		first = null;
		assertEquals(second,JavaNames.combineNames(first, second));
		first = "";
		assertEquals(second,JavaNames.combineNames(first, second));
		
		second = null;
		first = "firstName";
		assertEquals(first,JavaNames.combineNames(first, second));
		second = "";
		assertEquals(first,JavaNames.combineNames(first, second));
		
		first = null;
		second = null;
		assertNull(JavaNames.combineNames(first, second));
		
		first = "";
		second = "";
		assertEquals("",JavaNames.combineNames(first, second));
		
	}
	
	public void testArrayElementType() {
		String name = "String[]";
		assertEquals("String", JavaNames.getArrayElementType(name));
		name = "String[";
		assertEquals("String[", JavaNames.getArrayElementType(name));
		name = "[]";
		assertEquals("[]", JavaNames.getArrayElementType(name));
	}
	
	public void testIsJavaKeyword() {
		assertFalse(JavaNames.isJavaKeyword("hello"));
		assertTrue(JavaNames.isJavaKeyword("new"));
		assertTrue(JavaNames.isJavaKeyword("throws"));
		assertTrue(JavaNames.isJavaKeyword("final"));
		assertTrue(JavaNames.isJavaKeyword("static"));
		assertTrue(JavaNames.isJavaKeyword("class"));
	}
	
}
