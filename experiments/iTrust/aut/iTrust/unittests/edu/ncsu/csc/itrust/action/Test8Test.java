package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test8Test extends TestCase {
	ViewMyAccessLogAction action;
	private TestDataGenerator gen;

	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.hcp0();
		gen.hcp1();
		gen.hcp2();
		gen.patient1();
		gen.patient2();
		gen.patient23();
		gen.patient24();
		action = new ViewMyAccessLogAction(TestDAOFactory.getTestInstance(), 2L);
	}

	public void testGetRepresented() throws Exception {
		PatientDAO patientDAO = TestDAOFactory.getTestInstance().getPatientDAO();
		patientDAO.addRepresentative(9000000000L,2L);
		patientDAO.addRepresentative(9000000001L,2L);
		assertEquals(1L,action.getRepresented(2L).get(0).getMID());
		assertEquals(23L,action.getRepresented(24L).get(0).getMID());
		assertEquals(2L,action.getRepresented(9000000000L).get(0).getMID());
		assertEquals(2L,action.getRepresented(9000000001L).get(0).getMID());		
	}
	
	public void testGetRepresentedException() throws iTrustException {
		try {
		PatientDAO patientDAO = TestDAOFactory.getTestInstance().getPatientDAO();
		patientDAO.addRepresentative(2L,1L);
		fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals("Patient 2 already represents patient 1", e.getMessage());
		}	
	}
}
