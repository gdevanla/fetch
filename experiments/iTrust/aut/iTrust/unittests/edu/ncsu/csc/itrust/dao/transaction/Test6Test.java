package edu.ncsu.csc.itrust.dao.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import edu.ncsu.csc.itrust.beans.TransactionBean;
import edu.ncsu.csc.itrust.dao.mysql.TransactionDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.enums.TransactionType;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test6Test  extends TestCase {
	private TransactionDAO tranDAO = TestDAOFactory.getTestInstance().getTransactionDAO();
	private TestDataGenerator gen;
	
	@Override
	protected void setUp() throws Exception {
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.transactionLog();
	}
	
	public void testGetAllRecordAccesses() throws Exception {
		List<TransactionBean> list = tranDAO.getAllRecordAccesses(0L, 9000000000L,false);
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
		
		List<TransactionBean> list1 = tranDAO.getAllRecordAccesses(2L, 9000000001L,false);
		assertEquals(5, list1.size());
	
	}
	
	public void testGetRecordAccesses() throws Exception {
		String start = "2007-06-23 06:54:57";
		String end = "2007-06-23 06:55:58";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = formatter.parse(start);
		Date endDate = formatter.parse(end);
		
		List<TransactionBean> list = tranDAO.getRecordAccesses(2L, 9000000001L,startDate,endDate,false);
		assertEquals(3, list.size());
		//The gen.transactionLog() is adding a few logs for us to test
		//the sql.transactionLog file adds 3 items for secondayMID = 0L
		//The transaction type is understood from the transaction code
		//eg: 410 is DEMOGRAPHICS_EDIT
		assertEquals(9000000000L, list.get(0).getLoggedInMID());
		assertEquals(2,list.get(0).getSecondaryMID());
		assertEquals(TransactionType.PRESCRIPTION_REPORT_VIEW,list.get(0).getTransactionType());
		assertEquals("Viewed patient records",list.get(0).getAddedInfo());
		
		assertEquals(9000000000L, list.get(1).getLoggedInMID());
		assertEquals(2,list.get(1).getSecondaryMID());
		assertEquals(TransactionType.PRESCRIPTION_REPORT_VIEW,list.get(1).getTransactionType());
		assertEquals("Viewed patient records",list.get(1).getAddedInfo());
		
		assertEquals(9000000000L, list.get(2).getLoggedInMID());
		assertEquals(2,list.get(2).getSecondaryMID());
		assertEquals(TransactionType.PRESCRIPTION_REPORT_VIEW,list.get(2).getTransactionType());
		assertEquals("Viewed patient records",list.get(2).getAddedInfo());
		
		
	
	}
}
