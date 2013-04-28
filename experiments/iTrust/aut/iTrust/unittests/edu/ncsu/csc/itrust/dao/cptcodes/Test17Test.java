package edu.ncsu.csc.itrust.dao.cptcodes;

import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.ProcedureBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.CPTCodesDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test17Test extends TestCase {

	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private CPTCodesDAO cptCodesDAO = factory.getCPTCodesDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testGetAllCPTCodes() throws Exception {
		
		assertEquals(17,cptCodesDAO.getAllCPTCodes().size());
	
		ProcedureBean pb = cptCodesDAO.getAllCPTCodes().get(0);
		assertEquals("1270F", pb.getCPTCode());
		assertEquals("Injection procedure", pb.getDescription());
		
		pb = cptCodesDAO.getAllCPTCodes().get(1);
		assertEquals("87", pb.getCPTCode());
		assertEquals("Diagnostic Radiology", pb.getDescription());
		
		pb = cptCodesDAO.getAllCPTCodes().get(2);
		assertEquals("90371", pb.getCPTCode());
		assertEquals("Hepatitis B", pb.getDescription());
		
		pb = cptCodesDAO.getAllCPTCodes().get(3);
		assertEquals("90396", pb.getCPTCode());
		assertEquals("Varicella", pb.getDescription());
		
	}
	
	public void testGetImmunizationCPTCodes() throws Exception {
		
		assertEquals(15,cptCodesDAO.getImmunizationCPTCodes().size());
	
		ProcedureBean pb = cptCodesDAO.getAllCPTCodes().get(4);
		assertEquals("90633", pb.getCPTCode());
		assertEquals("Hepatitis A", pb.getDescription());
		assertEquals("immunization", pb.getAttribute());
		
		pb = cptCodesDAO.getAllCPTCodes().get(5);
		assertEquals("90645", pb.getCPTCode());
		assertEquals("Haemophilus influenzae", pb.getDescription());
		assertEquals("immunization", pb.getAttribute());
		
		pb = cptCodesDAO.getAllCPTCodes().get(6);
		assertEquals("90649", pb.getCPTCode());
		assertEquals("Human Papillomavirus", pb.getDescription());
		assertEquals("immunization", pb.getAttribute());
		
		pb = cptCodesDAO.getAllCPTCodes().get(7);
		assertEquals("90655", pb.getCPTCode());
		assertEquals("Influenza virus vaccine, split", pb.getDescription());
		assertEquals("immunization", pb.getAttribute());
		
	}
	
	public void testGetProcedureCPTCodes() throws Exception {
		
		assertEquals(2,cptCodesDAO.getProcedureCPTCodes().size());
	
		ProcedureBean pb = cptCodesDAO.getAllCPTCodes().get(0);
		assertEquals("1270F", pb.getCPTCode());
		assertEquals("Injection procedure", pb.getDescription());
		assertEquals(null, pb.getAttribute());
		
		pb = cptCodesDAO.getAllCPTCodes().get(1);
		assertEquals("87", pb.getCPTCode());
		assertEquals("Diagnostic Radiology", pb.getDescription());
		assertEquals(null, pb.getAttribute());
		
	}
	
	public void testGetCPTCode() throws Exception {
		
		ProcedureBean pb = cptCodesDAO.getCPTCode("90649");
		assertEquals("Human Papillomavirus", pb.getDescription());
		assertEquals("immunization", pb.getAttribute());
		
		pb.setDescription("Test Description");
		pb.setAttribute("test attribute");
		
		cptCodesDAO.updateCode(pb);
		ProcedureBean newPb = cptCodesDAO.getCPTCode("90649");
		assertEquals("Test Description", newPb.getDescription());
		assertEquals("test attribute", newPb.getAttribute());
	}
	
	public void testAddNewCPTCode() throws Exception {
		
		assertEquals(17,cptCodesDAO.getAllCPTCodes().size());
		
		ProcedureBean proc = new ProcedureBean();
		proc.setDescription("Test Description");
		proc.setAttribute("test attribute");
		proc.setCPTCode("11111");
		cptCodesDAO.addCPTCode(proc);
		assertEquals(18,cptCodesDAO.getAllCPTCodes().size());
		ProcedureBean newPb = cptCodesDAO.getCPTCode("11111");
		assertEquals("Test Description", newPb.getDescription());
		assertEquals("test attribute", newPb.getAttribute());
		
	}
	
}
