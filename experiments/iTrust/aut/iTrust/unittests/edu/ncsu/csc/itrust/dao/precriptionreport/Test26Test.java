package edu.ncsu.csc.itrust.dao.precriptionreport;


import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.PrescriptionReportBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.PrescriptionReportDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test26Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private PrescriptionReportDAO prescriptionReportDAO = factory.getPrescriptionReportDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testByDate() throws Exception{
		String startDate = "2006-10-10";
		String endDate = "2006-10-11";
		assertEquals(3,prescriptionReportDAO.byDate(2L, startDate, endDate).size());
		
		PrescriptionReportBean pres = prescriptionReportDAO.byDate(2L, startDate, endDate).get(0);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(5,pres.getPrescription().getDosage());
		assertEquals("Tetracycline",pres.getPrescription().getMedication().getDescription());
		assertEquals("Take twice daily",pres.getPrescription().getInstructions());
		assertEquals(2L,pres.getOfficeVisit().getPatientID());
		
		pres = prescriptionReportDAO.byDate(2L, startDate, endDate).get(1);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(5,pres.getPrescription().getDosage());
		assertEquals("Tetracycline",pres.getPrescription().getMedication().getDescription());
		assertEquals("Take twice daily",pres.getPrescription().getInstructions());
		assertEquals(2L,pres.getOfficeVisit().getPatientID());
		
		pres = prescriptionReportDAO.byDate(2L, startDate, endDate).get(2);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(5,pres.getPrescription().getDosage());
		assertEquals("Prioglitazone",pres.getPrescription().getMedication().getDescription());
		assertEquals("Take twice daily",pres.getPrescription().getInstructions());
		assertEquals(2L,pres.getOfficeVisit().getPatientID());
	}
	
	public void testByOfficeVisitAndPatients() throws DBException {
		List<Long> ovIDs = new ArrayList<Long>();
		ovIDs.add(1055L);	
		ovIDs.add(1056L);
		ovIDs.add(1057L);
		ovIDs.add(1058L);
		
		assertEquals(6,prescriptionReportDAO.byOfficeVisitAndPatient(ovIDs, 99L).size());
		
		PrescriptionReportBean pres = prescriptionReportDAO.byOfficeVisitAndPatient(ovIDs, 99L).get(0);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(5,pres.getPrescription().getDosage());
		assertEquals("Tetracycline",pres.getPrescription().getMedication().getDescription());
		assertEquals("Take twice daily",pres.getPrescription().getInstructions());
		assertEquals(99L,pres.getOfficeVisit().getPatientID());
		
		pres = prescriptionReportDAO.byOfficeVisitAndPatient(ovIDs, 99L).get(1);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(20,pres.getPrescription().getDosage());
		assertEquals("Tetracycline",pres.getPrescription().getMedication().getDescription());
		assertEquals("Eat like candy",pres.getPrescription().getInstructions());
		assertEquals(99L,pres.getOfficeVisit().getPatientID());
		
		pres = prescriptionReportDAO.byOfficeVisitAndPatient(ovIDs, 99L).get(2);
		assertEquals("Sun Jun 10 00:00:00 CDT 2007",pres.getOfficeVisit().getVisitDate().toString());
		assertEquals(20,pres.getPrescription().getDosage());
		assertEquals("Aspirin",pres.getPrescription().getMedication().getDescription());
		assertEquals("Eat like candy",pres.getPrescription().getInstructions());
		assertEquals(99L,pres.getOfficeVisit().getPatientID());
	}
}
