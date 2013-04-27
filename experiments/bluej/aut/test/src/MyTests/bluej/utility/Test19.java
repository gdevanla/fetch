package MyTests.bluej.utility;

import java.util.ArrayList;
import java.util.Iterator;

import junit.framework.TestCase;

import bluej.utility.MultiIterator;

/**
 * Testing bluej.utility.MultiIterator.java class that combines
 * multiple iterators into one.
 * 
 * @author Nikhat Farha
 *
 */

public class Test19 extends TestCase {
	protected void setUp() {
		//nothing to do
	}
	protected void tearDown() {
		//nothing to do
	}
	
	public void testMultiIterator() {
		ArrayList<String> strList1 = new ArrayList<String>();
		ArrayList<Integer> strList2 = new ArrayList<Integer>();
		ArrayList<Iterator<? extends Object>> iteratorList = new ArrayList<Iterator<? extends Object>>();
		
		strList1.add("List1 Test String 1");
		strList1.add("List1 Test String 2");
		strList2.add(10000);
		strList2.add(10001);
	
		Iterator<String> itr1 = strList1.iterator();
		Iterator<Integer> itr2 = strList2.iterator();
		
		iteratorList.add(itr1);
		iteratorList.add(itr2);
		
		//Combines multiple iterators into one and links multiple lists
		//as one
		MultiIterator<Object> multiIterator = new MultiIterator<Object>(iteratorList);
		
		assertTrue(multiIterator.hasNext());
		assertEquals("List1 Test String 1",multiIterator.next());
		assertTrue(multiIterator.hasNext());
		assertEquals("List1 Test String 2",multiIterator.next());
		assertTrue(multiIterator.hasNext());
		assertEquals(10000,multiIterator.next());
		assertTrue(multiIterator.hasNext());
		assertEquals(10001,multiIterator.next());
	
	}
	
	public void testNonExistentMembers() {
		
		ArrayList<Iterator<? extends Object>> iteratorList = new ArrayList<Iterator<? extends Object>>();
		MultiIterator<Object> multiIterator = new MultiIterator<Object>(iteratorList);
		assertFalse(multiIterator.hasNext());
		assertNull(multiIterator.next());
	}
	
	public void testRemove() {
		try {
			ArrayList<String> strList1 = new ArrayList<String>();
			ArrayList<Integer> strList2 = new ArrayList<Integer>();
			ArrayList<Iterator<? extends Object>> iteratorList = new ArrayList<Iterator<? extends Object>>();
			strList1.add("List1 Test String 1");
			strList2.add(10000);
		
			Iterator<String> itr1 = strList1.iterator();
			Iterator<Integer> itr2 = strList2.iterator();
			iteratorList.add(itr1);
			iteratorList.add(itr2);
			
			MultiIterator<Object> multiIterator = new MultiIterator<Object>(iteratorList);
			multiIterator.remove();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) {
			//success
		}
	}
	

}
