package edu.ncsu.csc.itrust.dao.diagnoses;

import java.util.Date;
import java.text.SimpleDateFormat;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.beans.DiagnosisBean;
import edu.ncsu.csc.itrust.beans.DiagnosisStatisticsBean;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.DiagnosesDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test20Test extends TestCase {
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	private DiagnosesDAO diagnosesDAO = factory.getDiagnosesDAO();
	
	@Override
	protected void setUp() throws Exception {
		TestDataGenerator gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();			
	}
	
	public void testEdit() throws DBException {
		try {
			assertEquals(2,diagnosesDAO.getList(11).size());
			DiagnosisBean db = diagnosesDAO.getList(11).get(0);
			assertEquals("Osteoarthrosis, generalized, multiple sites",db.getDescription());
			assertEquals("715.09",db.getICDCode());
			assertEquals("yes",db.getClassification());
			assertEquals("Osteoarthrosis, generalized, multiple sites(715.09)",db.getFormattedDescription());
					
			db.setDescription("Changed description");
			db.setICDCode("350.0");
			
			diagnosesDAO.edit(db);				
			assertEquals(1,diagnosesDAO.getList(11).size());
		}
		catch(Exception e) {
			fail();
		}
	}
	
	public void testGetWeeklyCounts() throws DBException {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse("2005-10-04");
			Date endDate = format.parse("2005-10-18");
			
			assertEquals(3,diagnosesDAO.getWeeklyCounts("487.00", "28215", startDate, endDate).size());
			DiagnosisStatisticsBean db = diagnosesDAO.getWeeklyCounts("487.00", "28215", startDate, endDate).get(0);
			assertEquals("Mon Oct 03 00:00:00 CDT 2005",db.getStartDate().toString());
			assertEquals("Sun Oct 09 00:00:00 CDT 2005",db.getEndDate().toString());
			
			db = diagnosesDAO.getWeeklyCounts("487.00", "28215", startDate, endDate).get(1);
			assertEquals("Mon Oct 10 00:00:00 CDT 2005",db.getStartDate().toString());
			assertEquals("Sun Oct 16 00:00:00 CDT 2005",db.getEndDate().toString());
			
			db = diagnosesDAO.getWeeklyCounts("487.00", "28215", startDate, endDate).get(2);
			assertEquals("Mon Oct 17 00:00:00 CDT 2005",db.getStartDate().toString());
			assertEquals("Sun Oct 23 00:00:00 CDT 2005",db.getEndDate().toString());
		}
		catch(Exception e) {
			fail();
		}
	}

	public void testGetDiagnosisCounts() throws DBException {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse("2005-10-04");
			Date endDate = format.parse("2005-10-18");		
			
			DiagnosisStatisticsBean db = diagnosesDAO.getDiagnosisCounts("487.00", "28215", startDate, endDate);
			assertEquals("Tue Oct 04 00:00:00 CDT 2005",db.getStartDate().toString());
			assertEquals("Tue Oct 18 00:00:00 CDT 2005",db.getEndDate().toString());
			assertEquals("28215",db.getZipCode());			
		}
		catch(Exception e) {
			fail();
		}
	}
	
	public void testGetCountForWeekOf() throws DBException {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = format.parse("2005-10-03");	
			
			DiagnosisStatisticsBean db = diagnosesDAO.getCountForWeekOf("487.00", "28215", startDate);
			assertEquals("Mon Oct 03 00:00:00 CDT 2005",db.getStartDate().toString());
			assertEquals("Sun Oct 09 00:00:00 CDT 2005",db.getEndDate().toString());
			assertEquals("28215",db.getZipCode());	
		}
		catch(Exception e) {
			fail();
		}
	}
}
