package MyTests.bluej.debugger.gentype;

import bluej.debugger.gentype.TextType;
import junit.framework.TestCase;

/**
 * Testing the bluej.debugger.gentype.TextType.java class
 * 
 * @author Nikhat Farha
 *
 */


public class Test5 extends TestCase{
	
	TextType textType = new TextType("test");
	
	protected void setUp() {
		//nothing to do
	}
	protected void tearDown() {
		//nothing to do
	}
	
	public void testTextType() {
		TextType textType = new TextType("test");
		assertEquals("test",textType.toString());
	}
	
	public void testCouldHold() {
		try {
			assertTrue(textType.couldHold(1));
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testEquals() {
		try {
			assertFalse(textType.equals(null));
		}
		catch(UnsupportedOperationException e) { fail(); }
	}
	
	public void testIsIntegralType() {
		try {
			assertTrue(textType.isIntegralType());
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testIsInterface() {
		try {
			assertFalse(textType.isInterface());
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testArrayComponentName() {
		try {
			textType.arrayComponentName();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testGetCapture() {
		try {
			textType.getCapture();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testTypeIs() {
		try {
			textType.typeIs(1);
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testIsPrimitive() {
		try {
			textType.isPrimitive();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) {
			//success
		}
	}

	public void testGetErasedType() {
		try {
			textType.getErasedType();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testIsAssignableFrom() {
		try {
			textType.isAssignableFrom(textType);
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) {
			//success
		}
	}
	
	public void testIsAssignableFromRaw() {
		try {
			textType.isAssignableFromRaw(textType);
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testIsNumeric() {
		try {
			textType.isNumeric();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) {
			//success
		}
	}
	
	public void testAsClass() {
		try {
			textType.asClass();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testGetLowerBound() {
		try {
			textType.getLowerBound();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testGetUpperBounds() {
		try {
			textType.getUpperBounds();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testGetArray() {
		try {
			textType.getArray();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testIsWildCard() {
		try {
			textType.isWildcard();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	public void testGetReferenceSupertypes() {
		try {
			textType.getReferenceSupertypes();
			fail("Exception should have been thrown");
		}
		catch(UnsupportedOperationException e) { /*success */ }
	}
	
	
	
	
}
