package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.RemoteMonitoringDataBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.RemoteMonitoringDataBeanValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test38Test extends TestCase {
	
	
	public void testPatientAllCorrect() throws Exception {
		RemoteMonitoringDataBean p = new RemoteMonitoringDataBean();
		p.setSystolicBloodPressure(100);
		p.setDiastolicBloodPressure(100);
		p.setGlucoseLevel(100);
		p.setPedometerReading(100);
		p.setHeight(70);
		p.setWeight(100);
		
		try {
			new RemoteMonitoringDataBeanValidator().validate(p);			
		} catch (FormValidationException e) {
			fail();
		}
	}
	
	
	public void testPatientAllErrors() throws Exception {
		RemoteMonitoringDataBean p = new RemoteMonitoringDataBean();
		p.setSystolicBloodPressure(10);
		p.setDiastolicBloodPressure(10);
		p.setGlucoseLevel(500);
		p.setPedometerReading(-2333);
		p.setHeight(555555);
		p.setWeight(555555);
		
		try {
			new RemoteMonitoringDataBeanValidator().validate(p);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals("Systolic Blood Pressure: " + ValidationFormat.SYSTOLIC_BLOOD_PRESSURE.getDescription(), e.getErrorList().get(0));
			assertEquals("Diastolic Blood Pressure: " + ValidationFormat.DIASTOLIC_BLOOD_PRESSURE.getDescription(), e.getErrorList().get(1));
			assertEquals("Glucose Level: " + ValidationFormat.GLUCOSE_LEVEL.getDescription(), e.getErrorList().get(2));
			assertEquals("Pedometer Reading: " + ValidationFormat.PEDOMETER_READING.getDescription(), e.getErrorList().get(3));
			assertEquals("Height: " + ValidationFormat.HEIGHT.getDescription(), e.getErrorList().get(4));
			assertEquals("Weight: " + ValidationFormat.WEIGHT.getDescription(), e.getErrorList().get(5));
			assertEquals("number of errors", 6, e.getErrorList().size());
		}
	}
}
