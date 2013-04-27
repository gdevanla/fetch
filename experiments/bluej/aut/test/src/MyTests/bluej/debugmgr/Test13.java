package MyTests.bluej.debugmgr;

import junit.framework.TestCase;

//Testing the classes IndexHistory,CallHistory and ClassHistory
import bluej.debugmgr.CallHistory;
import bluej.debugmgr.ClassHistory;
import bluej.debugmgr.IndexHistory;

/**
 * Testing the bluej.debugmgr.CallHistory.java,
 * bluej.debugmgr.ClassHistory.java and
 * bluej.debugmgr.IndexHistory.java
 * 
 * @author Nikhat Farha
 *
 */

public class Test13 extends TestCase{

	protected void setUp() {
		
	}
	protected void tearDown() {
		
	}
	public void testIndexHistory1() {
		IndexHistory indexHistory = new IndexHistory(4);
		indexHistory.add("first history");
		indexHistory.getPrevious();
		assertNull(indexHistory.getPrevious());
		indexHistory.add("second history");
		indexHistory.add("third history");
		indexHistory.add("fourth history");
		
		assertTrue("fourth history".equals(indexHistory.getPrevious()));
		assertTrue("third history".equals(indexHistory.getPrevious()));
		indexHistory.getNext();
		assertEquals("",indexHistory.getNext());
		assertNull(indexHistory.getNext());
	}
	
	public void testIndexHistory2() {
		IndexHistory indexHistory = new IndexHistory(6);
		indexHistory.add("first history");
		indexHistory.add("second history");
		indexHistory.add("third history");
		//Fourth empty element is added.
		//Thus the size is 4.
		assertEquals(4, indexHistory.getHistory().size());
		assertEquals("", indexHistory.getHistory().get(0));
		//The elements are retrieved in first in last out fashion.
		assertEquals("third history", indexHistory.getHistory().get(1));
		assertEquals("second history", indexHistory.getHistory().get(2));
		assertEquals("first history", indexHistory.getHistory().get(3));
		
	}
	
	public void testIndexHistory3() {
		IndexHistory indexHistory = new IndexHistory(2);
		//Number of elements added is greater than max length of history.
		indexHistory.add("first history");
		indexHistory.add("second history");
		indexHistory.add("third history");
		//Returns max history
		assertEquals(2, indexHistory.getHistory().size());
		assertEquals("", indexHistory.getHistory().get(0));
		//There is only the last history element and others are removed.
		assertEquals("third history", indexHistory.getHistory().get(1));
	}
	
	public void testClassHistory() {
		ClassHistory classHistory = ClassHistory.getClassHistory(10);
		// 7 commonly used class names are added to the history
		// 8th empty string is added to the history.
		assertEquals(8,classHistory.getHistory().size());
		assertEquals("",classHistory.getHistory().get(0));
		assertEquals("java.lang.String",classHistory.getHistory().get(1));
		assertEquals("java.lang.Math",classHistory.getHistory().get(2));
		assertEquals("java.util.ArrayList",classHistory.getHistory().get(3));
		assertEquals("java.util.Random",classHistory.getHistory().get(4));
		assertEquals("java.util.",classHistory.getHistory().get(5));
		assertEquals("java.awt.",classHistory.getHistory().get(6));
		assertEquals("javax.swing.",classHistory.getHistory().get(7));
		
		classHistory.add("Test13.java");
		assertEquals(8,classHistory.getHistory().size());
		assertEquals("Test13.java",classHistory.getHistory().get(0));
	}
	
	public void testCallHistory1() {
		CallHistory callHistory = new CallHistory();
		callHistory.addCall(Integer.class, "integer");
		callHistory.addCall(Boolean.class, "boolean");
		callHistory.addCall(Long.class, "long");
		
		assertEquals(1,callHistory.getHistory(Integer.class).size());
		assertEquals("integer",callHistory.getHistory(Integer.class).get(0));
		assertEquals(1,callHistory.getHistory(Boolean.class).size());
		assertEquals("boolean",callHistory.getHistory(Boolean.class).get(0));
		assertEquals(1,callHistory.getHistory(Long.class).size());
		assertEquals("long",callHistory.getHistory(Long.class).get(0));
		
		assertEquals(3,callHistory.getHistory(Object.class).size());
		//Retrieves objects in last in first out fashion
		assertEquals("long",callHistory.getHistory(Object.class).get(0));
		assertEquals("boolean",callHistory.getHistory(Object.class).get(1));
		assertEquals("integer",callHistory.getHistory(Object.class).get(2));
	}
	
}
