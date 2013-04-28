package edu.ncsu.csc.itrust.validate.bean;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.PersonnelBean;
import edu.ncsu.csc.itrust.exception.FormValidationException;
import edu.ncsu.csc.itrust.validate.PersonnelValidator;
import edu.ncsu.csc.itrust.validate.ValidationFormat;
import edu.ncsu.csc.itrust.validate.*;

public class Test36Test extends TestCase {
	
	MailValidator val = new MailValidator();
	
	public void testPatientAllCorrect() throws Exception {
		PersonnelBean p = new PersonnelBean();
		p.setFirstName("Nikhat");
		p.setLastName("Farha");
		p.setEmail("nikhatnikhat@gmail.com");
		p.setSecurityQuestion("'What is your fav sport?'-");
		p.setSecurityAnswer("football");
		p.setStreetAddress1("999 Desplaines Drive");
		p.setStreetAddress2("");
		p.setCity("Des Plaines");
		p.setState("IL");
		p.setZip("50016");
		p.setPhone("555");
		p.setPhone("222");
		p.setPhone("2222");
		p.setPassword("password");
		p.setConfirmPassword("password");
		new PersonnelValidator().validate(p);
	}
	
	public void testPatientAllErrors() throws Exception {
		PersonnelBean p = new PersonnelBean();
		p.setFirstName("Nikhat123");
		p.setLastName("Farha555");
		p.setEmail("nikhatnikhat?gmail.com");
		p.setStreetAddress1("9000 < Ave.");
		p.setStreetAddress2("?");
		p.setCity("Wr0ng");
		p.setState("desplaines");
		p.setZip("11111-");
		p.setPhone("555");
		
		p.setPassword("toooooooooooooooooooooooooo long password");
		p.setPassword("toooooooooooooooooooooooooo long password");
		try {
			new PersonnelValidator().validate(p);
			fail("exception should have been thrown");
		} catch (FormValidationException e) {
			assertEquals("First name: " + ValidationFormat.NAME.getDescription(), e.getErrorList().get(0));
			assertEquals("Last name: " + ValidationFormat.NAME.getDescription(), e.getErrorList().get(1));
			assertEquals(false, val.validateEmail("andy.programmer?gmail.com"));
			assertEquals("Street Address 1: " + ValidationFormat.ADDRESS.getDescription(), e.getErrorList().get(2));
			assertEquals("Street Address 2: " + ValidationFormat.ADDRESS.getDescription(), e.getErrorList().get(3));
			assertEquals("City: " + ValidationFormat.CITY.getDescription(), e.getErrorList().get(4));
			assertEquals("State: " + ValidationFormat.STATE.getDescription(), e.getErrorList().get(5));
			assertEquals("Zip Code: " + ValidationFormat.ZIPCODE.getDescription(), e.getErrorList().get(6));
			assertEquals("Phone Number: " + ValidationFormat.PHONE_NUMBER.getDescription(), e.getErrorList().get(7));
			assertEquals("number of errors", 9, e.getErrorList().size());
		}
	}
	
	
	/*
	 * Test for threat model - Last name too long.
	 */
	public void testPatientWithLongLastName() throws Exception {
		PersonnelBean p = new PersonnelBean();
		p.setFirstName("Person'a");
		p.setLastName("MyLastNameIsReallySuperDuperLoooooooong");
		p.setEmail("andy.programmer@gmail.com");
		p.setSecurityQuestion("'What is your quest?'-");
		p.setSecurityAnswer("I s33k the holy grail");
		p.setStreetAddress1("344 East Random Ave.");
		p.setStreetAddress2("");
		p.setCity("Des Plaines");
		p.setState("PA");
		p.setZip("17534");
		p.setPhone("555");
		p.setPhone("542");
		p.setPhone("9023");
		p.setPassword("testpass1");
		p.setConfirmPassword("testpass1");
		try {
			new PersonnelValidator().validate(p);
			fail();
		}
		catch(FormValidationException e){
			assertEquals("This form has not been validated correctly. " +
					"The following field are not properly filled in: [Last name: Up to 20 Letters, space, ' " +
					"and -, Phone Number: xxx-xxx-xxxx]", e.getMessage());
		}
	}
	
	
}
