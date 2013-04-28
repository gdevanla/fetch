package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.beans.RemoteMonitoringDataBean;
import edu.ncsu.csc.itrust.beans.TelemedicineBean;
import edu.ncsu.csc.itrust.dao.mysql.RemoteMonitoringDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test12Test extends TestCase {
	AddRemoteMonitoringDataAction action;
	private TestDataGenerator gen;
	private RemoteMonitoringDAO rmDAO = TestDAOFactory.getTestInstance().getRemoteMonitoringDAO();

	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.hcp0();
		gen.patient1();
		gen.patient3();
		action = new AddRemoteMonitoringDataAction(TestDAOFactory.getTestInstance(), 1, 1);
	}

	public void testAddRemoteMonitoringData() throws Exception {
		try {
			RemoteMonitoringDataBean b = new RemoteMonitoringDataBean();
			b.setGlucoseLevel(80);
			b.setSystolicBloodPressure(80);
			b.setDiastolicBloodPressure(80);
			action.addRemoteMonitoringData(b);
			TelemedicineBean tBean = new TelemedicineBean();
			assertTrue(rmDAO.addPatientToList(2L, 9000000000L, tBean));
			//These are true by default
			assertTrue(action.getTelemedicineBean(2).get(0).isDiastolicBloodPressureAllowed());
			assertTrue(action.getTelemedicineBean(2).get(0).isHeightAllowed());
			assertTrue(action.getTelemedicineBean(2).get(0).isPedometerReadingAllowed());
			assertEquals(1,action.getTelemedicineBean(2).size());
			
			TelemedicineBean tBean2 = new TelemedicineBean();
			tBean2.setDiastolicBloodPressureAllowed(false);
			tBean2.setHeightAllowed(false);
			tBean2.setPedometerReadingAllowed(false);
			assertTrue(rmDAO.addPatientToList(1L, 9000000000L, tBean2));
			
			
			assertEquals(1,action.getTelemedicineBean(1).size());
			assertFalse(action.getTelemedicineBean(1).get(0).isDiastolicBloodPressureAllowed());
			assertFalse(action.getTelemedicineBean(1).get(0).isHeightAllowed());
			assertFalse(action.getTelemedicineBean(1).get(0).isPedometerReadingAllowed());
			
		} catch(FormValidationException e) {
			fail();
		}
	}
	public void testGetPatientName() throws Exception {
		try {
			//Added by gen.patient1() method which runs the patient1.sql 
			assertEquals("Random Person",action.getPatientName(1));
			//Added by gen.patient2() method which runs the patient2.sql 
			assertEquals("Care Needs",action.getPatientName(3));
		} catch(DBException e) {
			fail();
		}
	}
}
