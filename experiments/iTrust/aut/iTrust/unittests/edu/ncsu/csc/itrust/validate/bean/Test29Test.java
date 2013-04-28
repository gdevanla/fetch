package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.ApptTypeBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.ApptTypeBeanValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test29Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		ApptTypeBean ab = new ApptTypeBean();
		ab.setName("Correct format");
		ab.setDuration(30);
		new ApptTypeBeanValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		ApptTypeBean ab = new ApptTypeBean();
		//Wrong format
		ab.setName(">");
		ab.setDuration(-1);
		try {
			new ApptTypeBeanValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(3, e.getErrorList().size());
			assertEquals("Appointment Type Name: " + ValidationFormat.APPT_TYPE_NAME.getDescription(), e
					.getErrorList().get(0));
			assertEquals("Appointment Type Duration: " + ValidationFormat.APPT_TYPE_DURATION.getDescription(), e
					.getErrorList().get(1));
		}
	}
}
