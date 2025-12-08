package xyz.peasfultown.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xyz.peasfultown.dao.DepartmentDAO;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLDepartmentDAO implements DepartmentDAO {
	private static final String CREATE_STRG = "INSERT INTO department (department_name) VALUE (?)";
	private static final String READ_ALL_STRG = "SELECT * FROM department";
	private static final String READ_BY_ID_STRG = "SELECT * FROM department WHERE department_id = ?";
	private static final String UPDATE_STRG = "UPDATE department SET department_name = ? WHERE department_id = ?";
	private static final String DELETE_STRG = "DELETE FROM department WHERE department_id = ?";

	@Override
	public void create(String depName) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			prstmt = con.prepareStatement(CREATE_STRG);
			prstmt.setString(1, depName);
			prstmt.executeUpdate();
			
			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

	@Override
	public List<Department> read() throws DatabaseException {
		List<Department> deps = new ArrayList<>();
		try (Connection con = JDBCUtils.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(READ_ALL_STRG)) {
			while (rs.next()) {
				Department dep = new Department(rs.getInt(1), rs.getString(2));
				deps.add(dep);
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
		return deps;
	}

	@Override
	public Department read(int id) throws DatabaseException {
		ResultSet rs = null;
		Department dep = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_BY_ID_STRG)) {
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				dep = new Department(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return dep;
	}

	@Override
	public void update(int id, String newName) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try  {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(UPDATE_STRG);
			prstmt.setString(1, newName);
			prstmt.setInt(2, id);
			prstmt.executeUpdate();
			
			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

//	@Override
//	public void delete(int id) throws DatabaseException {
//		Connection con = null;
//		PreparedStatement prstmt = null;
//		try {
//			con = JDBCUtils.getConnection();
//			con.setAutoCommit(false);
//			
//			prstmt = con.prepareStatement(DELETE_STRG);
//			prstmt.setInt(1, id);
//			prstmt.executeUpdate();	
//			
//			con.commit();
//		} catch (SQLException sqle) {
//			JDBCUtils.rollback(con);
//			throw new DatabaseException(sqle.getMessage(), sqle);
//		} finally {
//			JDBCUtils.close(con);
//			JDBCUtils.close(prstmt);
//		}
//	}
	
	// USING STORED PROCEDURE
	@Override
	public void delete(int id) throws DatabaseException {
		final String DELETE_SP_CALL = "{CALL sp_delete_department(?)}";
		Connection con = null;
		CallableStatement cs = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			cs = con.prepareCall(DELETE_SP_CALL);
			cs.setInt(1, id);
			cs.executeUpdate();
			
			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(cs);
		}
	}
}
