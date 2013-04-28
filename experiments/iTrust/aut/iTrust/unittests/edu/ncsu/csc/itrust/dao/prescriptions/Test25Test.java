package edu.ncsu.csc.itrust.dao.prescriptions;

import java.sql.Timestamp;
import java.util.Date;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.MedicationBean;
import edu.ncsu.csc.itrust.beans.PrescriptionBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.PrescriptionsDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test25Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private PrescriptionsDAO prescriptionsDAO = factory.getPrescriptionsDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testGetList() throws Exception{
		assertEquals(3,prescriptionsDAO.getList(955).size());
		
		PrescriptionBean pres = prescriptionsDAO.getList(955).get(0);
		assertEquals(5, pres.getDosage());
		assertEquals("Take twice daily", pres.getInstructions());
		assertEquals("009042407", pres.getMedication().getNDCode());
		assertEquals("10/11/2006", pres.getEndDateStr());
		assertEquals("10/10/2006", pres.getStartDateStr());
	}
	
	public void testAdd() throws DBException {
		PrescriptionBean pres = new PrescriptionBean();
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		MedicationBean med = prescriptionsDAO.getList(955).get(0).getMedication();
		pres.setDosage(10);
		pres.setVisitID(955);
		pres.setEndDate(tsNow);
		pres.setStartDateStr("10/11/2006");
		pres.setInstructions("Test Instructions");
		pres.setMedication(med);
		prescriptionsDAO.add(pres);
		assertEquals(4,prescriptionsDAO.getList(955).size());
	}
	
	public void testAddNonExistentNDCode() throws DBException {
		PrescriptionBean pres = new PrescriptionBean();
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		MedicationBean med = new MedicationBean();
		pres.setDosage(10);
		pres.setVisitID(955);
		pres.setEndDate(tsNow);
		pres.setStartDateStr("10/11/2006");
		pres.setInstructions("Test Instructions");
		pres.setMedication(med);
		prescriptionsDAO.add(pres);
		//Since 333 is not a valid code, this DiagnosisBean is never added.
		//Thus, the getList methods returns empty list
		assertEquals(3,prescriptionsDAO.getList(955).size());
	}
	
	public void testRemove() throws DBException {
		assertEquals(3,prescriptionsDAO.getList(955).size());
		PrescriptionBean db = prescriptionsDAO.getList(955).get(0);
		prescriptionsDAO.remove(db.getId());
		assertEquals(2,prescriptionsDAO.getList(955).size());
	}
	
	public void testRemoveNonExistentBean() throws DBException {
		try {
			//Would do nothing if the OVMedicationID passes to remove is
			//non-existent
			prescriptionsDAO.remove(333);
		}
		catch(Exception e) {
			fail();
		}
		
	}
	
	public void testEdit() throws DBException {
		try {
			assertEquals(3,prescriptionsDAO.getList(955).size());
			PrescriptionBean pres = prescriptionsDAO.getList(955).get(0);
			assertEquals("Take twice daily", pres.getInstructions());
			assertEquals("009042407", pres.getMedication().getNDCode());
			assertEquals("10/11/2006", pres.getEndDateStr());
			assertEquals("10/10/2006", pres.getStartDateStr());
			assertEquals(5, pres.getDosage());
					
			pres.setDosage(10);
			pres.setEndDateStr("03/20/2013");
			pres.setStartDateStr("03/19/2013");
			pres.setInstructions("Test Instructions");
			
			prescriptionsDAO.edit(pres);	
			pres = prescriptionsDAO.getList(955).get(0);
			assertEquals("Test Instructions", pres.getInstructions());
			assertEquals("03/20/2013", pres.getEndDateStr());
			assertEquals("03/19/2013", pres.getStartDateStr());
			assertEquals(10, pres.getDosage());
		}
		catch(Exception e) {
			fail();
		}
	}

	

}
