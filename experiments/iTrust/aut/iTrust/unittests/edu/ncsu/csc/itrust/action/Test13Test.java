package edu.ncsu.csc.itrust.action;

import java.text.SimpleDateFormat;
import java.util.List;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.AllergyBean;
import edu.ncsu.csc.itrust.beans.DiagnosisBean;
import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.beans.PrescriptionBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test13Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	private EmergencyReportAction action;

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
		gen.patient2();
		gen.hcp0();
		gen.ndCodes();
		gen.icd9cmCodes();
		action = new EmergencyReportAction(factory, 9000000000L, "2");
	}

	public void testBaseCase1() throws Exception {
		assertEquals("O-", action.getBloodType());
		
		List<AllergyBean> lab = action.getAllergies();
		assertEquals(2, lab.size());
		assertEquals("Pollen", lab.get(0).getDescription());
		assertEquals("06/05/2007", lab.get(0).getFirstFoundStr());
		assertEquals("Penicillin", lab.get(1).getDescription());
		assertEquals("06/05/2007", lab.get(0).getFirstFoundStr());
		
		List<PrescriptionBean> lpb = action.getCurrentPrescriptions();
		assertEquals(1, lpb.size());
		assertEquals("Prioglitazone", lpb.get(0).getMedication().getDescription());
		
		assertEquals("Male",action.getPatientGender());
		
		
		long pid = action.getPid();
		PatientBean pb = factory.getPatientDAO().getPatient(pid);
		long ageInMs = System.currentTimeMillis()
				- new SimpleDateFormat("MM/dd/yyyy").parse(pb.getDateOfBirthStr()).getTime();
		long age = ageInMs / (1000L * 60L * 60L * 24L * 365L);
		
		assertEquals(age,Integer.parseInt(action.getPatientAge()));
	}
	
	public void testBaseCase2() throws Exception {
		assertEquals(0, action.getImmunizations().size());
		
		long pid = action.getPid();
		PatientBean pb = factory.getPatientDAO().getPatient(pid);
		
		String emergencyContactName = pb.getEmergencyName();
		assertEquals("Mr Emergency",emergencyContactName);
		
		String emergencyContactPhone = pb.getEmergencyPhone();
		assertEquals("555-555-5551",emergencyContactPhone);
		
		String emergencyContactInfo = emergencyContactName + " " + emergencyContactPhone;
		assertEquals(emergencyContactInfo,action.getPatientEmergencyContact());
		
		List<DiagnosisBean> ldb = action.getWarningDiagnoses();
		assertEquals(2, ldb.size());
		assertEquals("Diabetes with ketoacidosis",ldb.get(0).getDescription());
		assertEquals("Coxsackie",ldb.get(1).getDescription());
		assertEquals(955,ldb.get(0).getVisitID());
		assertEquals(960,ldb.get(1).getVisitID());
		assertEquals("250.10",ldb.get(0).getICDCode());
		assertEquals("79.30",ldb.get(1).getICDCode());
	}

}
