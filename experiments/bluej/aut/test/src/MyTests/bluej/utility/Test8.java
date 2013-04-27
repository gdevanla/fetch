package MyTests.bluej.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bluej.parser.symtab.Selection;
import bluej.utility.FileEditor;
import bluej.utility.JavaUtils14;

import junit.framework.TestCase;

/**
 * Testing the class FileEditor.java and JavaUtils14.java
 * @author Nikhat Farha
 *
 */

public class Test8 extends TestCase {
	
	File file = null;
	BufferedWriter bw = null;
	protected void setUp() throws IOException {
		String currentDir =  System.getProperty("user.dir");
		String fileName = currentDir + File.separator +  "fileName8.txt";
		file = new File(fileName);
		file.createNewFile();
		FileWriter fw = new FileWriter(file);
		bw = new BufferedWriter(fw);
		bw.write("Hello!!!!!!!!!!");
		bw.flush();
	}
	
	protected void tearDown() throws IOException {
		bw.close();
	}
	
	public void testFileEditor() throws IOException {
		String currentDir =  System.getProperty("user.dir");
		String fileName = currentDir + File.separator +  "fileName8.txt";
		File file = new File(fileName);
		file.createNewFile();
		try {
			FileEditor fileEditor = new FileEditor(file);
			assertEquals(15,fileEditor.getLength());
			
		}
		catch(IOException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	public void testFileReplaceSelection() throws IOException {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			FileEditor fileEditor = new FileEditor(file);
			
			Selection s = new Selection(1, 6);
			fileEditor.replaceSelection(s, "*******");
			fileEditor.save();
			assertEquals(22,fileEditor.getLength());
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			assertEquals("Hello*******!!!!!!!!!!",br.readLine());
			s = new Selection(1, 6, 16);
			fileEditor.replaceSelection(s, "@@@@@@");
			fileEditor.save();
			assertEquals(12,fileEditor.getLength());
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			assertEquals("Hello@@@@@@!",br.readLine());
		}
		catch(IOException e) {
			fail();
			e.printStackTrace();
		}
		finally {
			fr.close();
			br.close();
		}
		
	}
	
	public void testJavaUtilsParameterTypes() {
		Class<?> [] classArray = {File.class, FileReader.class};
		String[] stringArray = JavaUtils14.getParameterTypes(classArray);
		assertEquals("File", stringArray[0]);
		assertEquals("FileReader", stringArray[1]);
	}
	
	public void testJavaUtilsTypeName() {
		Class<?> className = File.class;
		String result = JavaUtils14.getTypeName(className);
		assertEquals("File", result);
		className = FileReader.class;
		result = JavaUtils14.getTypeName(className);
		assertEquals("FileReader", result);
		
	}
}

