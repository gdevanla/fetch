package edu.ncsu.csc.itrust.dao.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import junit.framework.TestCase;
import edu.ncsu.csc.itrust.DBUtil;
import edu.ncsu.csc.itrust.dao.DAOFactory;
import edu.ncsu.csc.itrust.dao.mysql.AuthDAO;
import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.exception.DBException;
import edu.ncsu.csc.itrust.testutils.EvilDAOFactory;
import edu.ncsu.csc.itrust.testutils.TestDAOFactory;

public class Test4Test extends TestCase{
	private AuthDAO evilDAO = EvilDAOFactory.getEvilInstance().getAuthDAO();
	private DAOFactory factory = TestDAOFactory.getTestInstance();
	AuthDAO authDAO = factory.getAuthDAO();
	private TestDataGenerator gen = new TestDataGenerator();
	private String ipAddr = "192.168.1.1";

	@Override
	protected void setUp() throws Exception {
		gen.clearAllTables();
	}
	
	public void testResetLoginPasswordFailures() throws Exception {
		addLoginFailure(5, new Timestamp(System.currentTimeMillis()));
		authDAO.resetLoginFailuresToZero(ipAddr);
		assertEquals(0, authDAO.getLoginFailures(ipAddr));
		
		int count = authDAO.getResetPasswordFailures(ipAddr);
		authDAO.recordResetPasswordFailure(ipAddr);
		assertEquals(++count, authDAO.getResetPasswordFailures(ipAddr));		
	}
	
	
	public void testGetResetPasswordFailuresException() {
		try {
			evilDAO.getResetPasswordFailures("");
			fail("DBException should have been thrown");
		} 
		
		catch (DBException e) {
			assertEquals(EvilDAOFactory.MESSAGE, e.getSQLException().getMessage());
		}
		
	}

	public void testResetLoginFailuresToZeroException() throws DBException,SQLException{
		try {
			evilDAO.resetLoginFailuresToZero("");
			fail("DBException should have been thrown");
		} 
		
		catch (DBException e) {
			assertEquals(EvilDAOFactory.MESSAGE, e.getSQLException().getMessage());
		}
	}

	
	private void addLoginFailure(int count, Timestamp lastFailure) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = factory.getConnection();
			ps = conn.prepareStatement("INSERT INTO LoginFailures(IPAddress,failureCount, lastFailure) "
					+ "VALUES(?,?,?)");
			ps.setString(1, ipAddr);
			ps.setInt(2, count);
			ps.setTimestamp(3, lastFailure);
			ps.executeUpdate();
		} catch (SQLException ex) {
			throw ex;
		} finally {
			DBUtil.closeConnection(conn, ps);
		}

	}
	
	
}
