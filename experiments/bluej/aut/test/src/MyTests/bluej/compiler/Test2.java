package MyTests.bluej.compiler;

import junit.framework.TestCase;
import bluej.compiler.Diagnostic;

/**
 * Testing the bluej.compiler.Diagnostic.java class
 * 
 * @author Nikhat Farha
 *
 */


public class Test2 extends TestCase{
	
	protected void setUp() {
        // nothing to do
    }
    
	 protected void tearDown() {
	        // nothing to do
	 }
	 
	 public void testDiagnostic1() {
		 Diagnostic diag = new Diagnostic(Diagnostic.ERROR, "Test Error");
		 assertEquals(Diagnostic.ERROR, diag.getType());
		 assertEquals("Test Error", diag.getMessage());
	 }
	 
	 public void testDiagnostic2() {
		 Diagnostic diag = new Diagnostic(Diagnostic.ERROR, "Test Error","file.txt",1,3,5,5);
		 assertEquals(Diagnostic.ERROR, diag.getType());
		 assertEquals("Test Error", diag.getMessage());
		 assertEquals("file.txt", diag.getFileName());
		 assertEquals(1, diag.getStartLine());
		 assertEquals(3, diag.getStartColumn());
		 assertEquals(5, diag.getEndLine());
		 assertEquals(5, diag.getEndColumn());
	 }
 
}
