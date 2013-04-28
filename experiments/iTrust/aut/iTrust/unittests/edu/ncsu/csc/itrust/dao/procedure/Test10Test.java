package edu.ncsu.csc.itrust.dao.procedure;

import java.util.List;
import edu.ncsu.csc.itrust.beans.ProcedureBean;
import edu.ncsu.csc.itrust.dao.mysql.ProceduresDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;
import junit.framework.TestCase;

public class Test10Test extends TestCase{
	private ProceduresDAO procDAO = TestDAOFactory.getTestInstance().getProceduresDAO();

	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.cptCodes();
		gen.officeVisit1();
		gen.officeVisit3();
		gen.officeVisit2();
	}
	
	public void testGetImmunizationList() throws Exception {
	//	assertEquals("no current procedures on office vist 1", 0, procDAO.getList(1).size());
		ProcedureBean bean = new ProcedureBean();
		bean.setVisitID(1);
		bean.setCPTCode("90655");
		long ovid = procDAO.add(bean);
		
		List<ProcedureBean> procs = procDAO.getImmunizationList(1);
		assertEquals(1, procs.size());
		assertEquals("Influenza virus vaccine, split",procs.get(0).getDescription());
		procDAO.remove(ovid);
		
		bean.setVisitID(355);
		bean.setCPTCode("90696");
		long ovid2 = procDAO.add(bean);		
		procs = procDAO.getImmunizationList(355);
		assertEquals(1, procs.size());
		assertEquals("Diphtheria, Tetanus, Pertussis",procs.get(0).getDescription());
		procDAO.remove(ovid2);
		
		bean.setVisitID(333);
		bean.setCPTCode("90645");
		procDAO.add(bean);	
		procs = procDAO.getImmunizationList(333);
		assertEquals(1, procs.size());
		assertEquals("Haemophilus influenzae",procs.get(0).getDescription());
	}
	
	public void testMedProceduresList() throws Exception {
			ProcedureBean bean = new ProcedureBean();
			bean.setVisitID(1);
			bean.setCPTCode("87");
			procDAO.add(bean);			
			bean.setCPTCode("1270F");
			procDAO.add(bean);
			
			List<ProcedureBean> procs = procDAO.getMedProceduresList(1);
			assertEquals(2, procs.size());
			assertEquals("Diagnostic Radiology",procs.get(0).getDescription());
			assertEquals(null,procs.get(0).getAttribute());
			assertEquals("Injection procedure",procs.get(1).getDescription());
			assertEquals(null,procs.get(1).getAttribute());		
			
			//If a immunization procedure is added, it is not retrieved as
			//a medical procedure.
			ProcedureBean bean1 = new ProcedureBean();
			bean1.setVisitID(333);
			bean1.setCPTCode("90645");
			procDAO.add(bean1);
			procs = procDAO.getMedProceduresList(333);
			assertEquals(0, procs.size());
		}
}
