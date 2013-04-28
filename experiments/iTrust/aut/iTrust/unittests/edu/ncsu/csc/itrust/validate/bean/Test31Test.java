package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.DrugInteractionBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.DrugInteractionValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;

public class Test31Test  extends TestCase {

	public void testCorrectFormat() throws Exception {
		DrugInteractionBean ab = new DrugInteractionBean();
		ab.setFirstDrug("1234");
		ab.setSecondDrug("12345");
		ab.setDescription("Correct format");
		new DrugInteractionValidator().validate(ab);
	}

	public void testWrongFormat() throws Exception {
		DrugInteractionBean ab = new DrugInteractionBean();
		//Wrong format
		ab.setSecondDrug("<>");
		ab.setFirstDrug("<>");
		ab.setDescription("<>");
		
		try {
			new DrugInteractionValidator().validate(ab);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals(3, e.getErrorList().size());
			assertEquals("FirstDrug: " + ValidationFormat.ND.getDescription(), e
					.getErrorList().get(0));
			assertEquals("SecondDrug: " + ValidationFormat.ND.getDescription(), e
					.getErrorList().get(1));
			assertEquals("description: " + ValidationFormat.DRUG_INT_COMMENTS.getDescription(), e
					.getErrorList().get(2));
		}
	}

}
