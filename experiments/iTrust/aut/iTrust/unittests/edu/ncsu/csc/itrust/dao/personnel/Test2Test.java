package edu.ncsu.csc.itrust.dao.personnel;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.PersonnelBean;
import edu.ncsu.csc.itrust.dao.mysql.HospitalsDAO;
import edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.Role;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test2Test extends TestCase{
	PersonnelDAO personnelDAO = TestDAOFactory.getTestInstance().getPersonnelDAO();
	HospitalsDAO hospitalDAO = TestDAOFactory.getTestInstance().getHospitalsDAO();

	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.hospitals();
	}
	
	public void testGetHospitalsList() throws Exception {
		long mid = personnelDAO.addEmptyPersonnel(Role.HCP);
		hospitalDAO.assignHospital(mid, "1");
		assertEquals(1, personnelDAO.getHospitals(mid).size());
	}
	
	public void testGetAllPersonnelList() throws Exception {
		long hcpLT = personnelDAO.addEmptyPersonnel(Role.LT);
		long hcpMID = personnelDAO.addEmptyPersonnel(Role.HCP);
		long hcpUAP = personnelDAO.addEmptyPersonnel(Role.UAP);
		
		//personnelDAO.getAllPersonnel() gets the personnels with role
		//hcp,uap or er
		
		assertNotSame(3,personnelDAO.getAllPersonnel().size());
		assertEquals(2,personnelDAO.getAllPersonnel().size());
		assertNotSame(hcpLT,personnelDAO.getAllPersonnel().get(0).getMID());
		assertEquals(hcpMID,personnelDAO.getAllPersonnel().get(0).getMID());
		assertEquals(hcpUAP,personnelDAO.getAllPersonnel().get(1).getMID());	
		
	}
	
	public void testGetPersonnelWithName() throws Exception {
		long mid = personnelDAO.addEmptyPersonnel(Role.HCP);
		PersonnelBean p = personnelDAO.getPersonnel(mid);
		p.setFirstName("FirstName");
		p.setLastName("LastName");
		personnelDAO.editPersonnel(p);
		assertEquals(1, personnelDAO.searchForPersonnelWithName("FirstName", "LastName").size());
		assertEquals("FirstName", personnelDAO.searchForPersonnelWithName("FirstName", "LastName").get(0).getFirstName());
		assertEquals("LastName", personnelDAO.searchForPersonnelWithName("FirstName", "LastName").get(0).getLastName());
	}
	
	public void testGetLabTechs() throws Exception {
		long midLT = personnelDAO.addEmptyPersonnel(Role.LT);
		personnelDAO.addEmptyPersonnel(Role.HCP);		
		assertEquals(1, personnelDAO.getLabTechs().size());
		assertEquals(midLT, personnelDAO.getLabTechs().get(0).getMID());
	}
	
}
