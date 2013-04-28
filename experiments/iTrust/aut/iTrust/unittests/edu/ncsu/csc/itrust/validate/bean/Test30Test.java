package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.AdverseEventBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.AdverseEventValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test30Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		AdverseEventBean ab = new AdverseEventBean();
		ab.setDescription("Correct format");
		new AdverseEventValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		AdverseEventBean ab = new AdverseEventBean();
		//Wrong format
		ab.setDescription("<>");
		
		try {
			new AdverseEventValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(1, e.getErrorList().size());
			assertEquals("comment: " + ValidationFormat.ADVERSE_EVENT_COMMENTS.getDescription(), e
					.getErrorList().get(0));
		}
	}

}
