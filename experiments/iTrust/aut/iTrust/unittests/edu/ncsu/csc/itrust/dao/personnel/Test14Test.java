package edu.ncsu.csc.itrust.dao.personnel;

import edu.ncsu.csc.itrust.action.base.PersonnelBaseAction;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.EvilDAOFactory;
import junit.framework.TestCase;

public class Test14Test  extends TestCase {
	
	TestDataGenerator gen;
	
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		
	}
	
	public void testWrongInput() {
		try {
			new PersonnelBaseAction(EvilDAOFactory.getEvilInstance(), "xxx");
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals(
					"Personnel ID is not a number: For input string: \"xxx\"", e
							.getMessage());
		}
	}
	public void testEvilDatabase() {
		try {
			new PersonnelBaseAction(EvilDAOFactory.getEvilInstance(), "1");
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals(
					"A database exception has occurred. Please see the log in the console for stacktrace", e
							.getMessage());
		}
	}
	public void testNullInput() {
		try {
			new PersonnelBaseAction(EvilDAOFactory.getEvilInstance(), null);
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals(
					"Personnel ID is not a number: null", e
							.getMessage());
		}
	}
}
