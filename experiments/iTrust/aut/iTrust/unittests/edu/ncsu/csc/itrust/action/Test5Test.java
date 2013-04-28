package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.beans.PatientBean;
import edu.ncsu.csc.itrust.beans.PersonnelBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.dao.mysql.PersonnelDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.Role;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test5Test  extends TestCase{
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private TestDataGenerator gen = new TestDataGenerator();
	private EditPatientAction action;

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
		gen.patient2();
	}
	public void testEditMessageFilter() throws Exception {
		action = new EditPatientAction(factory, 2l, "2");
		PatientDAO po = new PatientDAO(factory);
		PatientBean pb = po.getPatient(2);
		//assertEquals("", pb.getMessageFilter());
		pb.setFirstName("Nikhat");
		pb.setLastName("Farha");
		action.updateInformation(pb);
		//action.editMessageFilter("NewFilter");
		
		PatientBean pb2 = po.getPatient(2);
		assertEquals("Nikhat", pb2.getFirstName());
		assertEquals("Farha", pb2.getLastName());
		//assertEquals("NewFilter", pb2.getMessageFilter());

	}
	
	public void testHistory() throws Exception {
		action = new EditPatientAction(factory, 2l, "2");
		PatientDAO po = new PatientDAO(factory);
		PatientBean pb = po.getPatient(2);
		assertEquals(0, action.getHistory().size());
		assertFalse(action.hasHistory());
		pb.setFirstName("Nikhat");
		pb.setLastName("Farha");
		action.updateInformation(pb);
		assertTrue(action.hasHistory());
		assertEquals(1, action.getHistory().size());
		assertEquals("Nikhat",action.getHistory().get(0).getFirstName());
		assertEquals("Farha",action.getHistory().get(0).getLastName());
	}
	
	public void testGetEmployeeName() throws Exception {		
		PersonnelDAO personnel = factory.getPersonnelDAO();
		long mid = personnel.addEmptyPersonnel(Role.HCP);
		PersonnelBean pb = personnel.getPersonnel(mid);
		pb.setFirstName("Nikhat");
		pb.setLastName("Farha");
		personnel.editPersonnel(pb);
		action = new EditPatientAction(factory, mid, "2");
		assertEquals("Nikhat Farha", action.getEmployeeName(mid));

	}
}
