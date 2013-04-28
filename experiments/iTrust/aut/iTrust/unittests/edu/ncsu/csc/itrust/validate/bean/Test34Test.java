package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.MessageBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.MessageValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test34Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		MessageBean msg = new MessageBean();
		msg.setBody("Correct Format");
		msg.setSubject("Correct Format");
		new MessageValidator().validate(msg);
	}

	public void testWrongFormat() throws Exception {
		MessageBean msg = new MessageBean();
		//Wrong format
		msg.setBody("nikhat@yahoo.com");
		msg.setSubject("nikhat@yahoo.com");
		try {
			new MessageValidator().validate(msg);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(2, e.getErrorList().size());
			assertEquals("body: " + ValidationFormat.MESSAGES_BODY.getDescription(), e
					.getErrorList().get(0));
			assertEquals("subject: " + ValidationFormat.MESSAGES_SUBJECT.getDescription(), e
					.getErrorList().get(1));
		}
	}

}
