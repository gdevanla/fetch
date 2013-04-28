package edu.ncsu.csc.itrust.action;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.TransactionType;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test11Test extends TestCase {
	private ActivityFeedAction action;
	private DAOFactory factory;
	private long mid = 1L;
	TestDataGenerator gen;
	
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();
		
		this.factory = TestDAOFactory.getTestInstance();
		this.action = new ActivityFeedAction(factory, mid);
	}
	
	
	public void testRecentDate() {
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		Timestamp tsYesterday = new Timestamp(dNow.getTime() - 1000*60*60*24);
		Timestamp tsLongAgo = new Timestamp(dNow.getTime() - 1000*60*60*24*10);
		new SimpleDateFormat();
		assertEquals(0,ActivityFeedAction.recent(tsNow));
		assertEquals(1,ActivityFeedAction.recent(tsYesterday));
		//For days further away from the recent date, 2 is returned.
		assertEquals(2,ActivityFeedAction.recent(tsLongAgo));
	}
	
	public void testGetMessageAsSentence() {
		Date dNow = new Date();
		Timestamp tsNow = new Timestamp(dNow.getTime());
		Timestamp tsYesterday = new Timestamp(dNow.getTime() - 1000*60*60*24);
		Timestamp tsLongAgo = new Timestamp(dNow.getTime() - 1000*60*60*24*10);
		new SimpleDateFormat();
		
		String msg;
		
		msg = action.getMessageAsSentence("", tsNow, TransactionType.PATIENT_CREATE);
		assertTrue(msg.contains("today"));
		msg = action.getMessageAsSentence("", tsYesterday, TransactionType.DEMOGRAPHICS_EDIT);
		assertTrue(msg.contains("yesterday"));
		msg = action.getMessageAsSentence("", tsLongAgo, TransactionType.DEMOGRAPHICS_EDIT);
		assertTrue(msg.contains("edited your demographics"));
	}
	
}
