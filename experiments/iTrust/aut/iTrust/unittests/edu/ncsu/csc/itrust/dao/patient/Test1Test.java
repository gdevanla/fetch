package edu.ncsu.csc.itrust.dao.patient;

import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.Role;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test1Test  extends TestCase{
	//Initializes the DAOFactory object which is actually a TestDAOFactory object
		//and saves as the field in gen object.
		//Gen object can be used to run sql queries. All the connection details are l
		//loaded up.
		private TestDataGenerator gen = new TestDataGenerator();
		//Initializes the patientDAO object with some of its fields filled with 
		//required values from the TestDAOFactory object
		private PatientDAO patientDAO = TestDAOFactory.getTestInstance().getPatientDAO();
		private PersonnelDAO personnelDAO = TestDAOFactory.getTestInstance().getPersonnelDAO();

		@Override
		protected void setUp() throws Exception {
			gen.clearAllTables();
		}
		/*public void testAddEmptyPatient() throws Exception {
			long pid = patientDAO.addEmptyPatient();
			assertEquals(" ", patientDAO.getName(pid));
		}*/
		public void testAddingHistory() throws Exception {
			long pid = patientDAO.addEmptyPatient();
			PatientBean p = patientDAO.getPatient(pid);
			p.setFirstName("Lola");
			p.setLastName("Schaefer");	
			long hcpid = personnelDAO.addEmptyPersonnel(Role.ER);
			//edit patient in turn calls addHistory
			patientDAO.editPatient(p, 9000000003L);		
			assertTrue(patientDAO.hasHistory(pid));
			
			assertTrue(patientDAO.checkPatientExists(pid));
			assertEquals("Lola", patientDAO.getPatientHistory(pid).get(0).getFirstName());
			assertEquals("Schaefer", patientDAO.getPatientHistory(pid).get(0).getLastName());
			
			int size = patientDAO.getPatientHistory(pid).size();
			//Explicitly adding the history
			patientDAO.addHistory(pid, hcpid);		
			assertEquals(++size, patientDAO.getPatientHistory(pid).size());
			
			p.setEmail("l@cox.net");
			patientDAO.editPatient(p, 9000000003L);	
			//edit patient in turn calls addHistory
			assertEquals(++size, patientDAO.getPatientHistory(pid).size());
			assertEquals("l@cox.net", patientDAO.getPatientHistory(pid).get(size-1).getEmail());
		}
		
		public void testDeclaringHCPs() throws Exception {
			long pid = patientDAO.addEmptyPatient();	
			long hcpid = personnelDAO.addEmptyPersonnel(Role.ER);
			int hcpCount = 0;
			
			assertTrue(patientDAO.declareHCP(pid, hcpid));
			hcpCount++;
			assertTrue(patientDAO.checkDeclaredHCP(pid, hcpid));
			assertEquals(hcpid,patientDAO.getDeclaredHCPs(pid).get(hcpCount-1).getMID());
			assertTrue(patientDAO.undeclareHCP(pid, hcpid));
			hcpCount--;
			assertFalse(patientDAO.checkDeclaredHCP(pid, hcpid));
			assertEquals(hcpCount,patientDAO.getDeclaredHCPs(pid).size());
		
		}
		
		public void testRepresentatives() throws Exception {
			long pid1 = patientDAO.addEmptyPatient();
			long pid2 = 1L;
			gen.patient1();
			
			patientDAO.addRepresentative(pid1, pid2);
			assertTrue(patientDAO.represents(pid1, pid2));
			assertEquals(pid2,patientDAO.getRepresented(pid1).get(0).getMID());
			assertEquals(pid1,patientDAO.getRepresenting(pid2).get(0).getMID());
			
			patientDAO.removeRepresentative(pid1, pid2);
			assertFalse(patientDAO.represents(pid1, pid2));
			
			
			
		
		}
}
