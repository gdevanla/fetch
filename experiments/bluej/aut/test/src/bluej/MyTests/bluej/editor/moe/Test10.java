package MyTests.bluej.editor.moe;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
//Testing
import bluej.editor.moe.TextUtilities;
import junit.framework.TestCase;

/**
 * Testing the bluej.editor.moe.TextUtilities.java that would 
 * evaluate the text contents in the editor window.
 * 
 * @author Nikhat Farha
 *
 */


public class Test10 extends TestCase{
	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	
	public void testFindMatchingBracket() throws BadLocationException {
		
		JTextArea jt = new JTextArea();
		jt.append("N(Farha)");
		jt.append("Nikhat{loves food}Farh(");
		Document d = jt.getDocument();
		assertEquals(7,TextUtilities.findMatchingBracket(d,1));
		assertEquals(25,TextUtilities.findMatchingBracket(d,14));
		assertEquals(-1,TextUtilities.findMatchingBracket(d,30));
	}
	
	public void testFindMatchingBracket2() throws BadLocationException {
		
		JTextArea jt = new JTextArea();
		jt.append("N(Farha)");
		jt.append("Nikhat\"{loves food}Farh(");
		Document d = jt.getDocument();
		//The back slash is not counted as a character so the
		//index for { is 15 not 16
		assertEquals(-1,TextUtilities.findMatchingBracket(d,16));
		assertEquals(26,TextUtilities.findMatchingBracket(d,15));
		//If the back slash comes twice, both are considered.
		jt.append("\\Farha()");
		assertEquals(39,TextUtilities.findMatchingBracket(d,38));
		
	}
	
	public void testException() throws BadLocationException {
		JTextArea jt = new JTextArea();
		jt.append("N(Farha)");
		jt.append("Nikhat{loves food}Farh(");
		Document d = jt.getDocument();
		try {
			TextUtilities.findMatchingBracket(d, 35);
			fail("Exception should have been thrown");
		}
		catch(BadLocationException e) {
			//success
		}
	}
	
	public void testFindWordStart() {
		String line = "N(Farha) Nikhat is a programmer.";
		try {
		assertEquals(2,TextUtilities.findWordStart(line, 0, null));
		fail("Exception should have been thrown");
		}
		catch(StringIndexOutOfBoundsException e) {
			//success
		}
		
	}
	
	public void testFindWordStart2() {
		String line = "N(Farha) Nikhat is a programmer.";
		assertEquals(2,TextUtilities.findWordStart(line, 6, null));
		assertEquals(9,TextUtilities.findWordStart(line, 13, null));
		assertEquals(15,TextUtilities.findWordStart(line, 16, null));
		assertEquals(18,TextUtilities.findWordStart(line, 19, null));
	}
	
	public void testFindWordEnd() {
		String line = "N(Farha) Nikhat is a programmer.";
		try {
		assertEquals(2,TextUtilities.findWordEnd(line, 40, null));
		fail("Exception should have been thrown");
		}
		catch(StringIndexOutOfBoundsException e) {
			//success
		}
		
	}
	
	public void testFindWordEnd2() {
		String line = "N(Farha) Nikhat is a programmer.";
		assertEquals(7,TextUtilities.findWordEnd(line, 2, null));
		assertEquals(15,TextUtilities.findWordEnd(line, 9, null));
		assertEquals(16,TextUtilities.findWordEnd(line, 15, null));
		assertEquals(19,TextUtilities.findWordEnd(line, 18, null));
	}
}
