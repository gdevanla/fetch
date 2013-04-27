package MyTests.bluej.compiler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import bluej.compiler.WriterOutputStream;

import junit.framework.TestCase;

/**
 * JUnit Test on class methods of bluej.compiler.WriterOutputStream.java
 * @author Nikhat Farha
 *
 */

public class Test1 extends TestCase{
	
	BufferedWriter writer = null;
	WriterOutputStream wos = null;
	BufferedReader reader = null;
	FileReader fr = null;
	
	protected void setUp() throws IOException {
		File file = new File("test.txt");
		FileWriter fw = new FileWriter(file);
		writer = new BufferedWriter(fw);
		wos = new WriterOutputStream(writer);
    }
    
	 protected void tearDown() throws IOException {	      
	       wos.close();
	       reader.close();
	 }
	 
	 public void testWrite() throws IOException {
		 byte[] toWrite = {'h','e','l','l','o'};
		 wos.write(toWrite);
		 wos.flush();
		 
		 File file = new File("test.txt");
		 try {
			 // if file doesn't exists throw Exception
			 if (!file.exists()) {
				 throw new Exception(" Input File missing " + file);
			 }
			 //Make sure we have the read access to this file
			 if(file.canRead() != true) {
				 file.setReadable(true);
			 }
			 fr = new FileReader(file);
			 reader = new BufferedReader(fr);
			 String dataRead = reader.readLine();
			 assertEquals("hello",dataRead);
		 }
		 catch(Exception e) {
			 fail();
		 }
	 }
	 
	 
	 public void testWrite2() throws IOException {
		 byte[] toWrite = {'h','e','l','l','o'};
		 wos.write(toWrite,2,3);
		 wos.flush();
		 
		 File file = new File("test.txt");
		 try {
			 // if file doesn't exists throw Exception
			 if (!file.exists()) {
				 throw new Exception(" Input File missing " + file);
			 }
			 //Make sure we have the read access to this file
			 if(file.canRead() != true) {
				 file.setReadable(true);
			 }
			 fr = new FileReader(file);
			 reader = new BufferedReader(fr);
			 String dataRead = reader.readLine();
			 assertEquals("llo",dataRead);
		 }
		 catch(Exception e) {
			 fail();
		 }
	 }
}
