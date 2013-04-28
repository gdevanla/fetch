package edu.ncsu.csc.itrust.dao.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import edu.ncsu.csc.itrust.beans.OperationalProfile;
import edu.ncsu.csc.itrust.beans.TransactionBean;
import edu.ncsu.csc.itrust.dao.mysql.TransactionDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.TransactionType;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test7Test extends TestCase{
	private TransactionDAO tranDAO = TestDAOFactory.getTestInstance().getTransactionDAO();
	private TestDataGenerator gen;
	
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.transactionLog();
	}
	
	public void testGetTransactionsAffecting() throws Exception {
		String timeLimitOnLog = "2007-06-23 07:50:00";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = formatter.parse(timeLimitOnLog);

		List<TransactionBean> list = tranDAO.getTransactionsAffecting(0L, 9000000000L,date,3);
		assertEquals(3, list.size());
		//The gen.transactionLog() is adding a few logs for us to test
		//the sql.transactionLog file adds 3 items for secondayMID = 0L
		//The transaction type is understood from the transaction code
		//eg: 410 is DEMOGRAPHICS_EDIT
		assertEquals(1L, list.get(0).getLoggedInMID());
		assertEquals(0,list.get(0).getSecondaryMID());
		assertEquals(TransactionType.DEMOGRAPHICS_EDIT,list.get(0).getTransactionType());
		assertEquals("",list.get(0).getAddedInfo());
		
		assertEquals(2L, list.get(1).getLoggedInMID());
		assertEquals(0,list.get(1).getSecondaryMID());
		assertEquals(TransactionType.DEMOGRAPHICS_EDIT,list.get(1).getTransactionType());
		assertEquals("",list.get(1).getAddedInfo());
		
		assertEquals(9000000001L, list.get(2).getLoggedInMID());
		assertEquals(0,list.get(2).getSecondaryMID());
		assertEquals(TransactionType.PRECONFIRM_PRESCRIPTION_RENEWAL,list.get(2).getTransactionType());
		assertEquals("Added 290.3 Senile dementia with delirium",list.get(2).getAddedInfo());
		
	}
	
	public void testGetOperationalProfile() throws Exception {
		
		OperationalProfile profile = tranDAO.getOperationalProfile();
		//The gen.transactionLog() is adding a few logs for us to test
		//the sql.transactionLog file adds 3 items for secondayMID = 0L
		//The transaction type is understood from the transaction code
		//eg: 410 is DEMOGRAPHICS_EDIT
		assertEquals(2,profile.getNumPatientTransactions());
		assertEquals(6,profile.getNumPersonnelTransactions());
		assertEquals(8,profile.getNumTotalTransactions());
		
		HashMap<TransactionType,Integer> patientCount = profile.getPatientCount();
		HashMap<TransactionType,Integer> personnelCount = profile.getPersonnelCount();
		HashMap<TransactionType,Integer> totalCount = profile.getTotalCount();
		
		assertEquals(new Integer(2),patientCount.get(TransactionType.DEMOGRAPHICS_EDIT));
		assertEquals(new Integer(1),personnelCount.get(TransactionType.PRECONFIRM_PRESCRIPTION_RENEWAL));
		assertEquals(new Integer(5),personnelCount.get(TransactionType.PRESCRIPTION_REPORT_VIEW));
		
		assertEquals(new Integer(2),totalCount.get(TransactionType.DEMOGRAPHICS_EDIT));
		assertEquals(new Integer(1),totalCount.get(TransactionType.PRECONFIRM_PRESCRIPTION_RENEWAL));
		assertEquals(new Integer(5),totalCount.get(TransactionType.PRESCRIPTION_REPORT_VIEW));
		
	}
}
