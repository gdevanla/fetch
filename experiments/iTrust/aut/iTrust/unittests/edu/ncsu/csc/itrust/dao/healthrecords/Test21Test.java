package edu.ncsu.csc.itrust.dao.healthrecords;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.HealthRecord;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.HealthRecordsDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test21Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private HealthRecordsDAO healthRecordsDAO = factory.getHealthRecordsDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.patient1();
		gen.patient2();
		//gen.standardData();			
	}
	
	public void testGetAllHealthRecords() throws DBException {
		assertEquals(1,healthRecordsDAO.getAllHealthRecords(1L).size());
		HealthRecord hr = healthRecordsDAO.getAllHealthRecords(1L).get(0);
		assertEquals(100,hr.getBloodPressureDiastolic());
		assertEquals(40,hr.getCholesterolHDL());
		assertEquals(100,hr.getCholesterolLDL());
		assertEquals(24.409722222222225,hr.getBodyMassIndex());
		assertEquals(100,hr.getCholesterolTri());
		assertEquals(72.0,hr.getHeight());
		assertEquals(9000000000L,hr.getPersonnelID());
		
		assertEquals(2,healthRecordsDAO.getAllHealthRecords(2L).size());
		hr = healthRecordsDAO.getAllHealthRecords(2L).get(0);
		assertEquals(250,hr.getBloodPressureDiastolic());
		assertEquals(36,hr.getCholesterolHDL());
		assertEquals(215,hr.getCholesterolLDL());
		assertEquals(280,hr.getCholesterolTri());
		assertEquals(62.0,hr.getHeight());
		assertEquals(9000000000L,hr.getPersonnelID());
		
	}
	
	public void testAdd() throws DBException {
		assertEquals(1,healthRecordsDAO.getAllHealthRecords(1L).size());
		HealthRecord hr = new HealthRecord();
		
		hr.setPatientID(1L);
		hr.setBloodPressureDiastolic(100);
		hr.setCholesterolHDL(40);
		hr.setCholesterolLDL(100);
		hr.setCholesterolTri(100);
		hr.setHeight(72.0);
		hr.setPersonnelID(9000000000L);
		
		assertTrue(healthRecordsDAO.add(hr));
		assertEquals(2,healthRecordsDAO.getAllHealthRecords(1L).size());
		hr = healthRecordsDAO.getAllHealthRecords(1L).get(1);
		assertEquals(100,hr.getBloodPressureDiastolic());
		assertEquals(40,hr.getCholesterolHDL());
		assertEquals(100,hr.getCholesterolLDL());
		assertEquals(100,hr.getCholesterolTri());
		assertEquals(72.0,hr.getHeight());
		assertEquals(9000000000L,hr.getPersonnelID());
		
	}
	
	public void testNonExistingPatientRecords() throws DBException {
		assertEquals(0,healthRecordsDAO.getAllHealthRecords(3L).size());
	}
}
