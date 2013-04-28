package edu.ncsu.csc.itrust.dao.message;

import java.util.Date;
import java.sql.SQLException;
import edu.ncsu.csc.itrust.beans.MessageBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.MessageDAO;
import edu.ncsu.csc.itrust.dao.mysql.PatientDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test23Test extends TestCase{
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private MessageDAO messageDAO = factory.getMessageDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();	
	}
	
	public void testGetMessageFrom() throws SQLException {
		assertEquals(11,messageDAO.getMessagesFrom(1L).size());
		MessageBean msg = messageDAO.getMessagesFrom(1L).get(0);
		
		assertEquals("Appointment",msg.getSubject());
		assertEquals("I checked on that!",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(15,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2010-02-01 09:12:00.0",msg.getSentDate().toString());
	
	}
	
	public void testGetMessagesFromTimeAscending() throws SQLException {
		assertEquals(11,messageDAO.getMessagesFromTimeAscending(1L).size());
		
		MessageBean msg = messageDAO.getMessagesFromTimeAscending(1L).get(0);
	
		assertEquals("Old Medicine",msg.getSubject());
		assertEquals("Is it safe to take leftover penicillin from last year when I was sick?",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(13,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2009-12-02 11:15:00.0",msg.getSentDate().toString());
		
		Date lastDate = messageDAO.getMessagesFromTimeAscending(1L).get(0).getSentDate();
		Date nextDate = messageDAO.getMessagesFromTimeAscending(1L).get(1).getSentDate();
		
		assertTrue(lastDate.before(nextDate));
		
		lastDate = messageDAO.getMessagesFromTimeAscending(1L).get(9).getSentDate();
		nextDate = messageDAO.getMessagesFromTimeAscending(1L).get(10).getSentDate();
		assertTrue(lastDate.before(nextDate) || lastDate.equals(nextDate));
		
	}
	
	public void testGetMessagesFromNameAscending() throws SQLException, DBException, iTrustException {
		assertEquals(11,messageDAO.getMessagesFromNameAscending(1L).size());
		MessageBean msg = messageDAO.getMessagesFromNameAscending(1L).get(1);
		
		assertEquals("Aspirin Side Effects",msg.getSubject());
		assertEquals("After taking this aspirin, I feel a little woozy. Should I report this as an adverse event?",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(14,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2009-12-29 15:33:00.0",msg.getSentDate().toString());
		
		
		PatientDAO patientDAO = factory.getPatientDAO();
		for(int i = 0 ; i < (11-1) ; i++) {
			msg = messageDAO.getMessagesFromNameAscending(1L).get(i);
			String lastName = patientDAO.getPatient(msg.getFrom()).getLastName();
			msg = messageDAO.getMessagesFromNameAscending(1L).get(i+1);
			String nextName = patientDAO.getPatient(msg.getFrom()).getLastName();
			assertTrue(lastName.compareTo(nextName) <= 0);
		}
		
	}
	
	public void testGetMessagesFromNameDescending() throws SQLException, DBException, iTrustException {
		assertEquals(11,messageDAO.getMessagesFromNameDescending(1L).size());
		MessageBean msg = messageDAO.getMessagesFromNameDescending(1L).get(0);
		
		assertEquals("Appointment Reschedule",msg.getSubject());
		assertEquals("Can I reschedule my appointment for next Monday at 2PM?",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(9,msg.getMessageId());
		assertEquals(9000000003L,msg.getTo());
		assertEquals("2010-01-16 11:55:00.0",msg.getSentDate().toString());
		
		PatientDAO patientDAO = factory.getPatientDAO();
		for(int i = 0 ; i < (11-1) ; i++) {
			msg = messageDAO.getMessagesFromNameDescending(1L).get(i);
			String lastName = patientDAO.getPatient(msg.getFrom()).getLastName();
			msg = messageDAO.getMessagesFromNameDescending(1L).get(i+1);
			String nextName = patientDAO.getPatient(msg.getFrom()).getLastName();
			assertTrue(lastName.compareTo(nextName) >= 0);
				
		}
		
	}
	
}
