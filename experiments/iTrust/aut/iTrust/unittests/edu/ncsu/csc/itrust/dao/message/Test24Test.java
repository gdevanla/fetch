package edu.ncsu.csc.itrust.dao.message;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.MessageBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.MessageDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test24Test extends TestCase{
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private MessageDAO messageDAO = factory.getMessageDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();	
	}
	
	public void testAddMessage() throws SQLException {
		assertEquals(11,messageDAO.getMessagesFrom(1L).size());
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		MessageBean newMsg = new MessageBean();
		newMsg.setBody("Test Body!");
		newMsg.setSubject("Test subject");
		newMsg.setFrom(1L);
		newMsg.setTo(9000000003L);
		newMsg.setParentMessageId(100);
		newMsg.setSentDate(tsNow);
		
		messageDAO.addMessage(newMsg);
		assertEquals(12,messageDAO.getMessagesFrom(1L).size());
		MessageBean msg = messageDAO.getMessagesFrom(1L).get(0);
		
		assertEquals("Test subject",msg.getSubject());
		assertEquals("Test Body!",msg.getBody());
		assertEquals(1L,msg.getFrom());
		assertEquals(9000000003L,msg.getTo());
		//Compare excluding milliseconds
		//Date object is converted to zero millis while storing in database
		assertEquals(tsNow.toString().substring(0,tsNow.toString().length()-4),msg.getSentDate().toString().substring(0,msg.getSentDate().toString().length()-2));
	
	}
	
	public void testUpdateMessage() throws SQLException, DBException {
		assertEquals(11,messageDAO.getMessagesFrom(1L).size());
		MessageBean msg = messageDAO.getMessagesFrom(1L).get(1);	
		assertEquals(0,msg.getRead());
			
		messageDAO.updateRead(msg);
		msg = messageDAO.getMessagesFrom(1L).get(1);
		assertEquals(1,msg.getRead());
	
	}
	

}
