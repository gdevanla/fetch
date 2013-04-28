package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.ApptBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.ApptBeanValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test28Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		ApptBean ab = new ApptBean();
		ab.setComment("Correct format");
		new ApptBeanValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		ApptBean ab = new ApptBean();
		//Wrong format
		ab.setComment(">");
		try {
			new ApptBeanValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(1, e.getErrorList().size());
			assertEquals("Appointment Comment: " + ValidationFormat.APPT_COMMENT.getDescription(), e
					.getErrorList().get(0));
		}
	}
}
