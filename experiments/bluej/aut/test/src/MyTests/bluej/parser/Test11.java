package MyTests.bluej.parser;

import bluej.parser.SourceLocation;
import bluej.parser.SourceSpan;
import bluej.parser.symtab.Selection;
import junit.framework.TestCase;

/**
 * Testing the bluej.parser.SourceSpan.java
 * Testing the bluej.parser.SourceLocation.java
 * Testing the bluej.parser.symtab.Selection.java
 * @author Nikhat Farha
 *
 */
public class Test11 extends TestCase{

	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	
	public void testConstructor() {
		Selection selection = new Selection(1,2);
		//When line and column is given, start and end is set to same position
		assertEquals("Selection [<1,2>-<1,2>]",selection.toString());
		//Setting start position
		SourceLocation sloc = new SourceLocation(1,2);
		//For end, the number of characters = 2. End location will be 1-4
		SourceSpan sspan = new SourceSpan(sloc,2);
		Selection selection2 = new Selection(sspan);
		assertEquals("Selection [<1,2>-<1,4>]",selection2.toString() );
		//Setting the end as 2 chars selection from start 1-2
		Selection selection3 = new Selection(1,2,2);
		assertEquals("Selection [<1,2>-<1,4>]",selection3.toString() );
		
	}
	
	public void testCombineWith() {
		Selection selection = new Selection(1,2,2);
		assertEquals("Selection [<1,2>-<1,4>]",selection.toString() );
		SourceLocation sloc = new SourceLocation(2,8);
		SourceSpan sspan = new SourceSpan(sloc,2);
		Selection selection2 = new Selection(sspan);
		selection.combineWith(selection2);
		assertEquals("Selection [<1,2>-<2,10>]",selection.toString());
		
	}
	
	public void testExtendEnd() {
		Selection selection = new Selection(1,2,2);
		assertEquals("Selection [<1,2>-<1,4>]",selection.toString() );
		//Extension is done only if the end locatin is beyond and exlusive
		//of the start location
		SourceLocation sloc = new SourceLocation(3,5);
		selection.extendEnd(sloc);
		assertEquals("Selection [<1,2>-<3,5>]",selection.toString());
		//Since the end location is inter-twined with start location, there 
		//is not extension to the end location.
		SourceLocation sloc2 = new SourceLocation(2,10);
		selection.extendEnd(sloc2);
		assertEquals("Selection [<1,2>-<3,5>]",selection.toString());
		
	}
	
	public void testExtendEnd2() {
		Selection selection = new Selection(1,2,2);
		assertEquals("Selection [<1,2>-<1,4>]",selection.toString() );
		//Extension is done only if the end locatin is beyond and exlusive
		//of the start location
		selection.extendEnd(3,5);
		assertEquals("Selection [<1,2>-<3,5>]",selection.toString());
		//Since the end location is inter-twined with start location, there 
		//is not extension to the end location.
		selection.extendEnd(2,10);
		assertEquals("Selection [<1,2>-<3,5>]",selection.toString());
		
	}
	
	public void testIsOneLine() {
		SourceLocation sloc1 = new SourceLocation(1,2);
		SourceLocation sloc2 = new SourceLocation(1,5);
		SourceSpan sspan = new SourceSpan(sloc1,sloc2);
		
		assertTrue(sspan.isOneLine());
		
		assertTrue(sloc1.equals(sspan.getStartLocation()));
		assertTrue(sloc2.equals(sspan.getEndLocation()));
		
		assertEquals(1,sspan.getStartLine());
		assertEquals(1,sspan.getEndLine());
		
		assertEquals(2,sspan.getStartColumn());
		assertEquals(5,sspan.getEndColumn());
		
		assertEquals("<1,2>",sloc1.toString());
		assertEquals("<1,5>",sloc2.toString());
		assertEquals("<1,2>-<1,5>",sspan.toString());
		
	}
	
	public void testException() {
		try {
			new SourceLocation(-1,2);
			fail("Exception should have been thrown");
		}
		catch(IllegalArgumentException e) {
			//success
		}
		try {
			new SourceLocation(0,-2);
			fail("Exception should have been thrown");
		}
		catch(IllegalArgumentException e) {
			//success
		}
	}
}
