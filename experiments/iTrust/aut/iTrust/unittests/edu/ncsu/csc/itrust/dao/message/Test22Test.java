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

public class Test22Test extends TestCase{
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private MessageDAO messageDAO = factory.getMessageDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();	
	//	gen.messages();
	}
	
	public void testGetMessageFor() throws SQLException {
		assertEquals(1,messageDAO.getMessagesFor(1L).size());
		MessageBean msg = messageDAO.getMessagesFor(1L).get(0);
		
		assertEquals("Lab Procedure",msg.getSubject());
		assertEquals("Hey, I checked on that!",msg.getBody());
		assertEquals(9000000000L,msg.getFrom());
		assertEquals(8,msg.getMessageId());
		assertEquals(1,msg.getTo());
		assertEquals("2010-01-28 17:58:00.0",msg.getSentDate().toString());

	}
	
	public void testGetMessagesTimeAscending() throws SQLException {
		assertEquals(14,messageDAO.getMessagesFor(9000000000L).size());
		assertEquals(14,messageDAO.getMessagesTimeAscending(9000000000L).size());
		
		MessageBean msg = messageDAO.getMessagesTimeAscending(9000000000L).get(0);
		assertEquals("Old Medicine",msg.getSubject());
		assertEquals("Is it safe to take leftover penicillin from last year when I was sick?",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(13,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2009-12-02 11:15:00.0",msg.getSentDate().toString());
		
		Date lastDate = messageDAO.getMessagesTimeAscending(9000000000L).get(0).getSentDate();
		Date nextDate = messageDAO.getMessagesTimeAscending(9000000000L).get(1).getSentDate();
		
		assertTrue(lastDate.before(nextDate));
		
		lastDate = messageDAO.getMessagesTimeAscending(9000000000L).get(12).getSentDate();
		nextDate = messageDAO.getMessagesTimeAscending(9000000000L).get(13).getSentDate();
		assertTrue(lastDate.before(nextDate) || lastDate.equals(nextDate));
		
	}
	
	public void testGetMessagesNameAscending() throws SQLException, DBException, iTrustException {
		assertEquals(14,messageDAO.getMessagesNameAscending(9000000000L).size());
		MessageBean msg = messageDAO.getMessagesNameAscending(9000000000L).get(1);
		
		assertEquals("Aspirin Side Effects",msg.getSubject());
		assertEquals("After taking this aspirin, I feel a little woozy. Should I report this as an adverse event?",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(14,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2009-12-29 15:33:00.0",msg.getSentDate().toString());
		
		
		PatientDAO patientDAO = factory.getPatientDAO();
		for(int i = 0 ; i < (14-1) ; i++) {
			msg = messageDAO.getMessagesNameAscending(9000000000L).get(i);
			String lastName = patientDAO.getPatient(msg.getFrom()).getLastName();
			msg = messageDAO.getMessagesNameAscending(9000000000L).get(i+1);
			String nextName = patientDAO.getPatient(msg.getFrom()).getLastName();
			assertTrue(lastName.compareTo(nextName) <= 0);
		}
		
	}
	
	public void testGetMessagesNameDescending() throws SQLException, DBException, iTrustException {
		assertEquals(14,messageDAO.getMessagesNameDescending(9000000000L).size());
		MessageBean msg = messageDAO.getMessagesNameDescending(9000000000L).get(1);
		
		assertEquals("Scratchy Throat",msg.getSubject());
		assertEquals("I would like to schedule an appointment before my throat gets any worse. Thanks!",msg.getBody());
		assertEquals(2L,msg.getFrom());
		assertEquals(6,msg.getMessageId());
		assertEquals(9000000000L,msg.getTo());
		assertEquals("2010-02-02 13:03:00.0",msg.getSentDate().toString());
		
		PatientDAO patientDAO = factory.getPatientDAO();
		for(int i = 0 ; i < (14-1) ; i++) {
			msg = messageDAO.getMessagesNameDescending(9000000000L).get(i);
			String lastName = patientDAO.getPatient(msg.getFrom()).getLastName();
			msg = messageDAO.getMessagesNameDescending(9000000000L).get(i+1);
			String nextName = patientDAO.getPatient(msg.getFrom()).getLastName();
			assertTrue(lastName.compareTo(nextName) >= 0);
				
		}
		
	}
	
}
