package edu.ncsu.csc.itrust.dao.patient;

import java.util.List;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class RepresentativeTest extends TestCase {
	PatientDAO patientDAO = TestDAOFactory.getTestInstance().getPatientDAO();
	private TestDataGenerator gen;

	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.patient1();
		gen.patient2();
	}

	public void testGetRepresented() throws Exception {
		List<PatientBean> rep = patientDAO.getRepresented(2L);
		assertEquals(1, rep.size());
		assertEquals(1L, rep.get(0).getMID());
	}

	public void testGetNoneRepresented() throws Exception {
		assertEquals(0, patientDAO.getRepresented(1L).size());
	}

	public void testGetNonExistentRepresented() throws Exception {
		assertEquals(0, patientDAO.getRepresented(500L).size());
	}

	public void testRepresentsTrue() throws Exception {
		assertTrue(patientDAO.represents(2L, 1L));
	}

	public void testRepresentsFalse() throws Exception {
		assertFalse(patientDAO.represents(1L, 2L));
	}

	public void testAddRepresentative() throws Exception {
		assertEquals(0, patientDAO.getRepresented(1L).size());
		patientDAO.addRepresentative(1L, 2L);
		assertEquals(1, patientDAO.getRepresented(1L).size());
	}

	public void testAddExistingRepresentative() throws Exception {
		patientDAO.addRepresentative(1L, 2L);
		try {
			patientDAO.addRepresentative(1L, 2L);
			fail("exception should have been thrown");
		} catch (iTrustException e) {
			assertEquals("Patient 1 already represents patient 2", e.getMessage());
		}
	}
	
	public void testRemoveRepresentative() throws Exception {
		assertEquals(1, patientDAO.getRepresented(2L).size());
		boolean confirm = patientDAO.removeRepresentative(2L, 1L);
		assertEquals(0, patientDAO.getRepresented(2L).size());
		assertTrue(confirm);
	}

	public void testRemoveNonExistingRepresentative() throws Exception {
		assertEquals(1, patientDAO.getRepresented(2L).size());
		boolean confirm = patientDAO.removeRepresentative(2L, 3L);
		assertEquals(1, patientDAO.getRepresented(2L).size());
		assertFalse(confirm);
	}
}
