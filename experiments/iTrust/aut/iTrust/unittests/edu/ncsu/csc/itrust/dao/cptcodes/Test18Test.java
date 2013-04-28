package edu.ncsu.csc.itrust.dao.cptcodes;

import edu.ncsu.csc.itrust.beans.ProcedureBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.CPTCodesDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test18Test  extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private CPTCodesDAO cptCodesDAO = factory.getCPTCodesDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	
	public void testAddExistingCPTCode() {
		try {
			ProcedureBean proc = new ProcedureBean();
			proc.setDescription("Test Description");
			proc.setAttribute("test attribute");
			proc.setCPTCode("90649");
			cptCodesDAO.addCPTCode(proc);
			fail("Exception should have been thrown.");
		}
		catch(Exception e) {
			assertEquals("Error: Code already exists.", e.getMessage());
		}
	}
	
	public void testAddNullCPTCode() {
		try {
			ProcedureBean proc = new ProcedureBean();
			proc.setDescription(null);
			proc.setAttribute(null);
			proc.setCPTCode("1111");
			cptCodesDAO.addCPTCode(proc);
			fail("Exception should have been thrown.");
		}
		catch(Exception e) {
			assertEquals("A database exception has occurred. Please see the log in the console for stacktrace", e.getMessage());
		}
	}
	
	public void testGetNonExistingCPTCode() {
		try {
			//if the non-existingCPTCode is accessed, the method returns null
			ProcedureBean pb = cptCodesDAO.getCPTCode("999999999");
			assertNull(pb);
		}
		catch(Exception e) {
			fail();
		}
	}
}
