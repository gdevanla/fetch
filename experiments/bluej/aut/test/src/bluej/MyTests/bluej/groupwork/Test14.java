package MyTests.bluej.groupwork;

import java.io.File;

//import junit.framework.TestCase;

import MyTests.bluej.Mocks;
import bluej.groupwork.TeamStatusInfo;
import bluej.groupwork.TeamViewFilter;
import bluej.groupwork.UpdateFilter;

/**
 * Testing the bluej.groupwork.TeamStatusInfo.java and 
 * 				bluej.groupwork.UpdateFilter.java
 * @author farani50
 *
 */

public class Test14 extends Mocks{
	protected void setUp() throws SecurityException, IllegalArgumentException, NoSuchFieldException, IllegalAccessException {
		super.initMocks();
	}
	protected void tearDown() {
		
	}
	
	public void testTeamStatusInfo() {
		String currentDir = System.getProperty("user.dir");
		String fileName = currentDir + File.separator + "test14.txt";
		File file = new File(fileName);
		
		TeamStatusInfo statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSCHECKOUT);
		
		assertEquals(TeamStatusInfo.STATUS_NEEDSCHECKOUT, statusInfo.getStatus());
		assertEquals(fileName,statusInfo.getFile().getAbsolutePath());
		assertEquals("localVersion",statusInfo.getLocalVersion());
		assertEquals("remoteVersion",statusInfo.getRepositoryVersion());
		
		assertEquals("Added to repository",TeamStatusInfo.getStatusString(TeamStatusInfo.STATUS_NEEDSCHECKOUT));
		assertEquals("Modified in repository",TeamStatusInfo.getStatusString(TeamStatusInfo.STATUS_NEEDSUPDATE));
		assertEquals("Modified locally and in repository",TeamStatusInfo.getStatusString(TeamStatusInfo.STATUS_NEEDSMERGE));
		assertEquals("Added locally",TeamStatusInfo.getStatusString(TeamStatusInfo.STATUS_NEEDSADD));
	}
	
	public void testAccept() {
		UpdateFilter filter = new UpdateFilter();
		String currentDir = System.getProperty("user.dir");
		String fileName = currentDir + File.separator + "test14.txt";
		File file = new File(fileName);
		
		TeamStatusInfo statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSCHECKOUT);
		assertTrue(filter.accept(statusInfo));
		statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSMERGE);
		assertTrue(filter.accept(statusInfo));
		statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSUPDATE);
		assertTrue(filter.accept(statusInfo));
		
		TeamStatusInfo statusInfo1 = new TeamStatusInfo();
		assertFalse(filter.accept(statusInfo1));
	}
	
	public void testUpdateAlways() {
		UpdateFilter filter = new UpdateFilter();
		String currentDir = System.getProperty("user.dir");
		String fileName = currentDir + File.separator + "test14.txt";
		File file = new File(fileName);
		
		TeamStatusInfo statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSCHECKOUT);
		assertTrue(filter.updateAlways(statusInfo));
		statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_CONFLICT_LMRD);
		assertTrue(filter.updateAlways(statusInfo));
		statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_REMOVED);
		assertTrue(filter.updateAlways(statusInfo));
		
		//Any other status, returns false
		statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSADD);
		assertFalse(filter.updateAlways(statusInfo));
		TeamStatusInfo statusInfo1 = new TeamStatusInfo();
		assertFalse(filter.updateAlways(statusInfo1));
	}
	
	public void testTeamViewFilter() {
		String currentDir = System.getProperty("user.dir");
		String fileName = currentDir + File.separator + "test14.txt";
		File file = new File(fileName);
		
		TeamStatusInfo statusInfo = new TeamStatusInfo(file,"localVersion","remoteVersion",TeamStatusInfo.STATUS_NEEDSCHECKOUT);
		TeamViewFilter teamViewFilter = new TeamViewFilter();
		assertTrue(teamViewFilter.accept(statusInfo));
	}
}
