package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.OverrideReasonBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.OverrideReasonBeanValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test35Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		OverrideReasonBean or = new OverrideReasonBean();
		or.setORCode("1234");
		or.setDescription("Correct Format");
		new OverrideReasonBeanValidator().validate(or);
	}

	public void testWrongFormat() throws Exception {
		OverrideReasonBean or = new OverrideReasonBean();
		//Wrong format
		or.setORCode("<>");
		or.setDescription("<>");
		try {
			new OverrideReasonBeanValidator().validate(or);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(2, e.getErrorList().size());
			assertEquals("Reason Code: " + ValidationFormat.ORC.getDescription(), e
					.getErrorList().get(0));
			assertEquals("Description: " + ValidationFormat.OR_CODE_DESCRIPTION.getDescription(), e
					.getErrorList().get(1));
		}
	}

}
