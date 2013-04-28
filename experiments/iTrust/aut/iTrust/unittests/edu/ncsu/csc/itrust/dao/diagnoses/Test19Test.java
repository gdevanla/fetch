package edu.ncsu.csc.itrust.dao.diagnoses;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.DiagnosisBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.DiagnosesDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test19Test   extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private DiagnosesDAO diagnosesDAO = factory.getDiagnosesDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testGetList() throws Exception{
		assertEquals(2,diagnosesDAO.getList(11).size());
		DiagnosisBean db = diagnosesDAO.getList(11).get(0);
		assertEquals("Osteoarthrosis, generalized, multiple sites",db.getDescription());
		assertEquals("715.09",db.getICDCode());
		assertEquals("yes",db.getClassification());
		assertEquals("Osteoarthrosis, generalized, multiple sites(715.09)",db.getFormattedDescription());
	}
	
	public void testFindEarliestIncident() throws Exception{
		assertEquals("2005-10-10",diagnosesDAO.findEarliestIncident("250.00").toString());
		assertEquals("1985-10-10",diagnosesDAO.findEarliestIncident("250.1").toString());
		assertEquals("2005-10-10",diagnosesDAO.findEarliestIncident("350.0").toString());
		assertEquals("2005-10-10",diagnosesDAO.findEarliestIncident("487.00").toString());
		
	}
	
	public void testAdd() throws DBException {
		DiagnosisBean db = new DiagnosisBean();
		db.setICDCode("250.0");
		db.setVisitID(99999);
		db.setDescription("Test Diagnoses");
		diagnosesDAO.add(db);
		assertEquals(1,diagnosesDAO.getList(99999).size());
	}
	
	public void testAddNonExistentICDCode() throws DBException {
		DiagnosisBean db = new DiagnosisBean();
		db.setICDCode("333");
		db.setVisitID(99999);
		db.setDescription("Test Diagnoses");
		diagnosesDAO.add(db);
		//Since 333 is not a valid code, this DiagnosisBean is never added.
		//Thus, the getList methods returns empty list
		assertEquals(0,diagnosesDAO.getList(99999).size());
	}
	
	public void testRemove() throws DBException {
		assertEquals(2,diagnosesDAO.getList(11).size());
		DiagnosisBean db = diagnosesDAO.getList(11).get(0);
		diagnosesDAO.remove(db.getOvDiagnosisID());
		assertEquals(1,diagnosesDAO.getList(11).size());
	}
	
	public void testRemoveNonExistentBean() throws DBException {
		try {
			//Would do nothing if the OVDiagnosisID passes to remove is
			//non-existent
			diagnosesDAO.remove(323);
		}
		catch(Exception e) {
			fail();
		}
		
	}



}
