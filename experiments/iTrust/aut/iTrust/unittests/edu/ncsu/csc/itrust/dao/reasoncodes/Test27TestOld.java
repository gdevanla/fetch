package edu.ncsu.csc.itrust.dao.reasoncodes;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.OverrideReasonBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
//import edu.ncsu.csc.itrust.dao.mysql.ReasonCodesDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.exception.iTrustException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test27TestOld extends TestCase {
//	private DAOFactory factory = TestDAOFactory.getTestInstance();
//	private ReasonCodesDAO reasonCodesDAO = new ReasonCodesDAO(factory);
//	
//	@Override
//	protected void setUp() throws Exception {
//		TestDataGenerator gen = new TestDataGenerator();
//		gen.clearAllTables();
//		gen.standardData();			
//	}
//	
//	public void testGetAllORCodes() throws DBException {
//		assertEquals(8,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean orb = reasonCodesDAO.getAllORCodes().get(0);
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Alerted interaction not clincally significant", orb.getDescription());
//		assertEquals("00001", orb.getORCode());
//		
//		orb = reasonCodesDAO.getAllORCodes().get(1);
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Patient currently tolerates the medication or combination", orb.getDescription());
//		assertEquals("00002", orb.getORCode());
//	}
//	
//	public void testGetORCode() throws DBException {		
//		OverrideReasonBean orb = reasonCodesDAO.getORCode("00001");
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Alerted interaction not clincally significant", orb.getDescription());
//
//		orb = reasonCodesDAO.getORCode("00002");
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Patient currently tolerates the medication or combination", orb.getDescription());
//	}
//	
//	public void testGetNonExistentORCode() throws DBException {			
//		OverrideReasonBean orb = reasonCodesDAO.getORCode("11111");
//		assertNull(orb);
//	}
//	
//	public void testAddORCode() throws iTrustException {
//		assertEquals(8,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean orb = new OverrideReasonBean();
//		orb.setDescription("Test Description");
//		orb.setID(0);
//		orb.setORCode("11111");
//		orb.setPresID(0);
//		
//		reasonCodesDAO.addORCode(orb);
//		assertEquals(9,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean newOrb = reasonCodesDAO.getAllORCodes().get(8);
//		assertEquals(0, newOrb.getID());
//		assertEquals(0, newOrb.getPresID());
//		assertEquals("Test Description", newOrb.getDescription());
//		assertEquals("11111", newOrb.getORCode());
//		
//	}
//	
//	public void testUpdateORCode() throws iTrustException {
//		assertEquals(8,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean orb = reasonCodesDAO.getAllORCodes().get(0);
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Alerted interaction not clincally significant", orb.getDescription());
//		assertEquals("00001", orb.getORCode());
//		
//		orb.setDescription("Test Description");
//		reasonCodesDAO.updateCode(orb);
//		
//		OverrideReasonBean newOrb = reasonCodesDAO.getAllORCodes().get(0);
//		assertEquals("Test Description", newOrb.getDescription());
//		
//	}
//	
//	public void testUpdateORCodeWithDescription() throws iTrustException {
//		assertEquals(8,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean orb = reasonCodesDAO.getAllORCodes().get(0);
//		assertEquals(0, orb.getID());
//		assertEquals(0, orb.getPresID());
//		assertEquals("Alerted interaction not clincally significant", orb.getDescription());
//		assertEquals("00001", orb.getORCode());
//		
//		orb.setDescription("Test Description");
//		//Changing OR code doesn't do any update
//		orb.setORCode("11111");
//		reasonCodesDAO.updateCode(orb);
//		
//		assertEquals(8,reasonCodesDAO.getAllORCodes().size());
//		OverrideReasonBean newOrb = reasonCodesDAO.getAllORCodes().get(0);
//		assertNotSame("Test Description", newOrb.getDescription());
//		assertNotSame("11111", newOrb.getORCode());
//		
//	}

}
