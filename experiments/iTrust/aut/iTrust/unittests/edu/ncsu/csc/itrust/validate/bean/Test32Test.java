package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.forms.EditPrescriptionsForm;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.EditPrescriptionsValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test32Test extends TestCase {

	public void testCorrectFormat() throws Exception {
		EditPrescriptionsForm ab = new EditPrescriptionsForm();
		ab.setStartDate("03/03/2013");
		ab.setEndDate("03/07/2013");
		ab.setDosage("5");
		ab.setInstructions("Correct Format");
		new EditPrescriptionsValidator("default Instructions").validate(ab);
	}

	public void testWrongFormat() throws Exception {
		EditPrescriptionsForm ab = new EditPrescriptionsForm();
		//Wrong format
		ab.setStartDate("2013");
		ab.setEndDate("2013");
		ab.setDosage("<");
		ab.setInstructions("<>");
		
		try {
			new EditPrescriptionsValidator("default Instructions").validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(4, e.getErrorList().size());
			assertEquals("Start Date: " + ValidationFormat.DATE.getDescription(), e
					.getErrorList().get(0));
			assertEquals("End Date: " + ValidationFormat.DATE.getDescription(), e
					.getErrorList().get(1));
			assertEquals("Instructions: " + ValidationFormat.NOTES.getDescription(), e
					.getErrorList().get(2));
			assertEquals("Dosage must be an integer in [0,9999]" , e
					.getErrorList().get(3));
		}
	}

}
