package MyTests.bluej.parser;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Properties;
//Testing the class
import bluej.parser.symtab.ClassInfo;
import bluej.parser.symtab.Selection;
import junit.framework.TestCase;

/**
 * Testing the bluej.parser.symtab.ClassInfo.java and
 * bluej.parser.symtab.Selection.java classes
 * 
 * @author Nikhat Farha
 *
 */


public class Test12 extends TestCase {
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	public void testSetSuperClass() {
		ClassInfo info = new ClassInfo();
		assertFalse(info.foundPublicClass());
		info.setName("Test12.java", true);
		info.setSuperclass("junit.framework.TestCase");
		assertTrue(info.foundPublicClass());
		assertTrue(info.isUnitTest());
		assertFalse(info.isMIDlet());
		assertFalse(info.isApplet());
		info.setSuperclass("javax.microedition.midlet.MIDlet");
		assertTrue(info.isMIDlet());
		assertFalse(info.isApplet());
		assertTrue(info.isUnitTest());
		info.setSuperclass("javax.swing.JApplet");
		assertTrue(info.isApplet());
		assertTrue(info.isUnitTest());
		assertTrue(info.isMIDlet());
	}
	
	public void testSetComments() {
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		info.addComment("test target", "test comment", "test paramnames");
		
		Properties props = info.getComments();
		assertEquals("1", props.getProperty("numComments"));
		assertEquals("test comment", props.getProperty("comment0.text"));
		assertEquals("test target", props.getProperty("comment0.target"));
		assertEquals("test paramnames", props.getProperty("comment0.params"));
	}
	 
	public void testSetProperties() {
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		info.setEnum(true);
		assertTrue(info.isEnum());
		info.setInterface(true);
		assertTrue(info.isInterface());
		info.setAbstract(true);
		info.setParseError(true);
		assertTrue(info.isAbstract());
		assertTrue(info.hadParseError());
	}
	
	public void testAddProperties() {
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		info.addImplements("test interface");
		info.addImported("junit.framework.TestCase");
		info.addUsed("bluej.parser.symtab.ClassInfo");
		
		assertEquals(1,info.getImplements().size());
		assertEquals("test interface",info.getImplements().get(0));
		assertEquals(1,info.getUsed().size());
		assertEquals("bluej.parser.symtab.ClassInfo",info.getUsed().get(0));
	}
	
	public void testPrint() {
		//Set up output stream redirection
		System.setOut(new PrintStream(outContent));
		
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		info.setSuperclass("javax.microedition.midlet.MIDlet");
		info.addImplements("test interface");
		info.addImported("junit.framework.TestCase");
		info.addUsed("bluej.parser.symtab.ClassInfo");
		info.print();
		assertEquals(("\nsuperclass: javax.microedition.midlet.MIDlet\n\n" +
					"implements:\n" + "   " + "test interface\n\n" + "uses:\n" +
					"   " + "bluej.parser.symtab.ClassInfo\n\n" + "imports:\n" +
					"   " + "junit.framework.TestCase\n").replaceAll("\\s",""), outContent.toString().replaceAll("\\s",""));
		
		//Cleanup output stream redirection
		 System.setOut(null);
	}
	
	public void testSelections() {
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		
		Selection selection = new Selection(1,2);
		info.setExtendsInsertSelection(selection);
		assertEquals("Selection [<1,2>-<1,2>]",info.getExtendsInsertSelection().toString());
		info.setImplementsInsertSelection(selection);
		assertEquals("Selection [<1,2>-<1,2>]",info.getImplementsInsertSelection().toString());
		info.setExtendsReplaceSelection(selection);
		assertEquals("Selection [<1,2>-<1,2>]",info.getExtendsReplaceSelection().toString());
		info.setSuperReplaceSelection(selection);
		assertEquals("Selection [<1,2>-<1,2>]",info.getSuperReplaceSelection().toString());
		info.setTypeParametersSelection(selection);
		
		ArrayList<Selection> selections = new ArrayList<Selection>();
		selections.add(selection);
		info.setInterfaceSelections(selections);
		assertTrue(info.hasInterfaceSelections());
		assertEquals(1,info.getInterfaceSelections().size());
		assertEquals("Selection [<1,2>-<1,2>]",info.getInterfaceSelections().get(0).toString());
		assertTrue(info.hasTypeParameter());
	}
	
	public void testSetPackageSelections() {
		ClassInfo info = new ClassInfo();
		info.setName("Test12.java", true);
		
		Selection pkgStatement = new Selection(1,2);
		Selection pkgName = new Selection(1,3);
		Selection pkgSemi = new Selection(1,4);
		String pkgNameText = "test package name test";
		info.setPackageSelections(pkgStatement, pkgName, pkgNameText, pkgSemi);
		
		assertTrue(info.hasPackageStatement());
		assertEquals("Selection [<1,2>-<1,2>]",info.getPackageStatementSelection().toString());
		assertEquals("Selection [<1,3>-<1,3>]",info.getPackageNameSelection().toString());
		assertEquals("Selection [<1,4>-<1,4>]",info.getPackageSemiSelection().toString());
		assertEquals(pkgNameText,info.getPackage());
	}
	
}
