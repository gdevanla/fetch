package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.SurveyResultBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.SurveySearchValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test37Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		SurveyResultBean ab = new SurveyResultBean();
		ab.setHCPzip("12345");
		new SurveySearchValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		SurveyResultBean ab = new SurveyResultBean();
		//Wrong format
		ab.setHCPzip("?");
		try {
			new SurveySearchValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(1, e.getErrorList().size());
			assertEquals("Zip Code: " + ValidationFormat.ZIPCODE.getDescription(), e
					.getErrorList().get(0));
		}
	}

}
