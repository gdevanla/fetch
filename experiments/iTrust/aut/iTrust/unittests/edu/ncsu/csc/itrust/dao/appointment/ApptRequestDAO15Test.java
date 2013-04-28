//package edu.ncsu.csc.itrust.dao.appointment;
//
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.util.Date;
//import junit.framework.TestCase;
//import edu.ncsu.csc.itrust.beans.ApptBean;
//import edu.ncsu.csc.itrust.dao.DAOFactory;
//import edu.ncsu.csc.itrust.dao.mysql.ApptDAO;
//import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
//import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

package edu.ncsu.csc.itrust.dao.appointment;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.ApptBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.ApptDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class ApptRequestDAO15Test extends TestCase {

	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private ApptDAO apptDAO = factory.getApptDAO();
	
	//@Override1
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testGetApptForPatient() throws SQLException {		
		
		assertEquals(5,apptDAO.getApptsFor(1).size());
		assertEquals("General Checkup",apptDAO.getApptsFor(1).get(0).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(1).get(0).getHcp());
		assertEquals("Consultation",apptDAO.getApptsFor(1).get(1).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(1).get(1).getHcp());
		assertEquals("Ultrasound",apptDAO.getApptsFor(1).get(2).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(1).get(2).getHcp());
		assertEquals("Physical",apptDAO.getApptsFor(1).get(4).getApptType());
		assertEquals(9000000003L,apptDAO.getApptsFor(1).get(4).getHcp());
		
		assertEquals(9,apptDAO.getApptsFor(2).size());
		assertEquals("General Checkup",apptDAO.getApptsFor(2).get(0).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(2).get(0).getHcp());
		assertEquals("General Checkup",apptDAO.getApptsFor(2).get(1).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(2).get(1).getHcp());
		assertEquals("Colonoscopy",apptDAO.getApptsFor(2).get(2).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(2).get(2).getHcp());
		assertEquals("General Checkup",apptDAO.getApptsFor(2).get(3).getApptType());
		assertEquals(9000000003L,apptDAO.getApptsFor(2).get(3).getHcp());
		
	}
	
	public void testEditAppt() throws SQLException {		
		ApptBean appt = apptDAO.getApptsFor(1).get(0);
		appt.setApptType("Test Type");
		appt.setComment("Testing the comment field");
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		appt.setDate(tsNow);
		apptDAO.editAppt(appt);
		assertEquals("Test Type",apptDAO.getApptsFor(1).get(0).getApptType());
		assertEquals("Testing the comment field",apptDAO.getApptsFor(1).get(0).getComment());
		String expectedString = tsNow.toString().substring(0,tsNow.toString().length()-3);
		expectedString = expectedString + "0";
		assertEquals(expectedString,apptDAO.getApptsFor(1).get(0).getDate().toString());
		
	}
	
	
	public void testRemoveAppt() throws SQLException {		
		ApptBean appt = apptDAO.getApptsFor(1).get(0);
		assertEquals(5,apptDAO.getApptsFor(1).size());
		apptDAO.removeAppt(appt);
		assertEquals(4,apptDAO.getApptsFor(1).size());
		apptDAO.scheduleAppt(appt);
		assertEquals(5,apptDAO.getApptsFor(1).size());
		assertEquals("General Checkup",apptDAO.getApptsFor(1).get(0).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(1).get(0).getHcp());
	}
	
	public void testScheduleAppt() throws SQLException {		
		
		assertEquals(5,apptDAO.getApptsFor(1).size());
		ApptBean appt = apptDAO.getApptsFor(1).get(0);
		appt.setApptType("Test Type");
		appt.setComment("Testing the comment field");
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		appt.setDate(tsNow);
		apptDAO.scheduleAppt(appt);
		assertEquals(6,apptDAO.getApptsFor(1).size());
		assertEquals("Test Type",apptDAO.getApptsFor(1).get(1).getApptType());
		assertEquals(9000000000L,apptDAO.getApptsFor(1).get(1).getHcp());
		assertEquals("Testing the comment field",apptDAO.getApptsFor(1).get(1).getComment());
	}


}
