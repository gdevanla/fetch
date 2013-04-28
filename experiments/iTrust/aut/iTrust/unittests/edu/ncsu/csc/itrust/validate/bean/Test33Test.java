package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.MessageBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.EMailValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test33Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		MessageBean ab = new MessageBean();
		ab.setBody("Correct Format");
		new EMailValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		MessageBean ab = new MessageBean();
		//Wrong format
		ab.setBody("nikhat@yahoo.com");
		try {
			new EMailValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(1, e.getErrorList().size());
			assertEquals("body: " + ValidationFormat.EMAILS.getDescription(), e
					.getErrorList().get(0));
		}
	}

}
