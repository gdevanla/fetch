package edu.ncsu.csc.itrust.dao.personnel;

import edu.ncsu.csc.itrust.action.EditPersonnelAction;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.Role;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test3Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
	}

	public void testEditAuthorization() throws Exception {
		try {
			gen.hcp0();
			gen.uap1();
			new EditPersonnelAction(factory, 8000000009L, "9000000000");
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals("You are not authorized to edit this record!", e.getMessage());
		}
	}
	
	public void testUnauthorizedEdit() throws Exception {
		try {
			long mid = factory.getPersonnelDAO().addEmptyPersonnel(Role.UAP);
			gen.uap1();
			new EditPersonnelAction(factory, mid, "8000000009");
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals("User does not exist", e.getMessage());
		}
	}
}
