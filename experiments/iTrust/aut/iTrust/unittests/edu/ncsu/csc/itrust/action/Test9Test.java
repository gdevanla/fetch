package edu.ncsu.csc.itrust.action;

import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test9Test extends TestCase {
	private ViewMyRecordsAction action;
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	
	private long pid = 2L;
	private long hcpId = 9000000000L;

	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();

		action = new ViewMyRecordsAction(factory, pid);
		
	}

	public void testCompleteOfficeVisits() throws Exception {
		//The data is created by gen.standardData() in the setup
		
		assertEquals(hcpId,action.getCompleteOfficeVisit(1L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(11L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(111L).getHcpID());
		
		assertEquals(hcpId,action.getCompleteOfficeVisit(952L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(1000L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(2000L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(3000L).getHcpID());
		assertEquals(hcpId,action.getCompleteOfficeVisit(4000L).getHcpID());
		
		assertEquals(9900000000L,action.getCompleteOfficeVisit(1052L).getHcpID());
	}
	
	public void testGetProcedures() throws Exception {
		//The data is created by gen.standardData() in the setup
		
		assertEquals(1,action.getProcedures(955L).size());
		assertEquals("1270F",action.getProcedures(955L).get(0).getCPTCode());
		
		assertEquals(1,action.getProcedures(1000L).size());
		assertEquals("90371",action.getProcedures(1000L).get(0).getCPTCode());
		
		assertEquals(1,action.getProcedures(2000L).size());
		assertEquals("90371",action.getProcedures(2000L).get(0).getCPTCode());
		
		assertEquals(1,action.getProcedures(3000L).size());
		assertEquals("90371",action.getProcedures(3000L).get(0).getCPTCode());
		
		assertEquals(1,action.getProcedures(4000L).size());
		assertEquals("90371",action.getProcedures(4000L).get(0).getCPTCode());
		
	}
}
