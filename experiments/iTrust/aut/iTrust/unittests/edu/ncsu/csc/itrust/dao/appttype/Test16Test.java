package edu.ncsu.csc.itrust.dao.appttype;

import java.sql.SQLException;
import edu.ncsu.csc.itrust.beans.ApptTypeBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.ApptTypeDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test16Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private ApptTypeDAO apptTypeDAO = factory.getApptTypeDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testGetApptType() throws SQLException {		
		assertEquals(6,apptTypeDAO.getApptTypes().size());
		
		assertEquals("General Checkup",apptTypeDAO.getApptTypes().get(0).getName());
		assertEquals(45,apptTypeDAO.getApptTypes().get(0).getDuration());
		
		assertEquals("Mammogram",apptTypeDAO.getApptTypes().get(1).getName());
		assertEquals(60,apptTypeDAO.getApptTypes().get(1).getDuration());
		
		assertEquals("Physical",apptTypeDAO.getApptTypes().get(2).getName());
		assertEquals(15,apptTypeDAO.getApptTypes().get(2).getDuration());
		
		assertEquals("Colonoscopy",apptTypeDAO.getApptTypes().get(3).getName());
		assertEquals(90,apptTypeDAO.getApptTypes().get(3).getDuration());
		
		assertEquals("Ultrasound",apptTypeDAO.getApptTypes().get(4).getName());
		assertEquals(30,apptTypeDAO.getApptTypes().get(4).getDuration());
		
		assertEquals("Consultation",apptTypeDAO.getApptTypes().get(5).getName());
		assertEquals(30,apptTypeDAO.getApptTypes().get(5).getDuration());
	}
	
	public void testAddApptType() throws SQLException {		
		assertEquals(6,apptTypeDAO.getApptTypes().size());
		
		ApptTypeBean apptType = new ApptTypeBean();
		apptType.setDuration(100);
		apptType.setName("Test Type");
		
		apptTypeDAO.addApptType(apptType);
		
		assertEquals(7,apptTypeDAO.getApptTypes().size());
		
		assertEquals("Test Type",apptTypeDAO.getApptTypes().get(6).getName());
		assertEquals(100,apptTypeDAO.getApptTypes().get(6).getDuration());
	}
	
	public void testEditApptType() throws Exception {		
		assertEquals(6,apptTypeDAO.getApptTypes().size());
		
		ApptTypeBean apptType = apptTypeDAO.getApptTypes().get(0) ;
		assertEquals("General Checkup",apptType.getName());
		assertEquals(45,apptType.getDuration());
		
		apptType.setDuration(100);
		
		apptTypeDAO.editApptType(apptType);

		assertEquals(100,apptTypeDAO.getApptTypes().get(0).getDuration());
	}
}
