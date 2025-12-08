package xyz.peasfultown.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import xyz.peasfultown.dao.AccountDAO;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Gender;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLAccountDAO implements AccountDAO {
	private static final String CREATE_STRG = "INSERT INTO `account` (email, username, fullname, gender, department_id, position_id) VALUE (?, ?, ?, ?, ?, ?)";
	private static final String READ_ALL_STRG = "SELECT a.account_id, a.email, a.username, a.fullname, a.gender, "
			+ "d.department_id, d.department_name, p.position_id, p.position_name, a.create_date "
			+ "FROM `account` a LEFT JOIN department d ON a.department_id = d.department_id "
			+ "LEFT JOIN `position` p ON a.position_id = p.position_id";
	private static final String READ_BY_ID_STRG = "SELECT a.account_id, a.email, a.username, a.fullname, a.gender, "
			+ "d.department_id, d.department_name, p.position_id, p.position_name, a.create_date "
			+ "FROM `account` a LEFT JOIN department d ON a.department_id = d.department_id "
			+ "LEFT JOIN `position` p ON a.position_id = p.position_id WHERE a.account_id = ?";
	private static final String UPDATE_STRG = "UPDATE `account` SET email = ?, username = ?, fullname = ?, gender = ?, department_id = ?, position_id = ? WHERE account_id = ?";
	private static final String DELETE_STRG = "DELETE FROM `account` WHERE account_id = ?";

	@Override
	public void create(String email, String username, String fullname, Gender gender, Department department,
			Position position) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(CREATE_STRG);
			prstmt.setString(1, email);
			prstmt.setString(2, username);
			prstmt.setString(3, fullname);
			prstmt.setString(4, String.valueOf(gender.getGender()));
			prstmt.setInt(5, department.getId());
			prstmt.setInt(6, position.getId());
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
	public List<Account> read() throws DatabaseException {
		List<Account> accounts = new ArrayList<>();
		try (Connection con = JDBCUtils.getConnection(); 
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(READ_ALL_STRG)) {
			while (rs.next()) {
				int id = rs.getInt("account_id");
				String email = rs.getString("email");
				String username = rs.getString("username");
				String fullname = rs.getString("fullname");
				Gender gender = Gender.getGender(rs.getString("gender").charAt(0));
				Department dep = new Department(rs.getInt("department_id"), rs.getString("department_name"));
				Position pos = new Position(rs.getInt("position_id"), rs.getString("position_name"));
				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
				Account acc = new Account(id, email, username, fullname, gender, dep, pos, createDate);
				
				accounts.add(acc);
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
		return accounts;
	}

	@Override
	public Account read(int id) throws DatabaseException {
		ResultSet rs = null;
		Account acc = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_BY_ID_STRG)){
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				String email = rs.getString("email");
				String username = rs.getString("username");
				String fullname = rs.getString("fullname");
				Gender gender = Gender.getGender(rs.getString("gender").charAt(0));
				Department dep = new Department(rs.getInt("department_id"), rs.getString("department_name"));
				Position pos = new Position(rs.getInt("position_id"), rs.getString("position_name"));
				LocalDateTime createDate = rs.getTimestamp("create_date").toLocalDateTime();
				acc = new Account(id, email, username, fullname, gender, dep, pos, createDate);
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return acc;
	}

	@Override
	public void update(int id, String email, String username, String fullname, Gender gender, Department department,
			Position position, LocalDateTime createDate) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(UPDATE_STRG);
			prstmt.setString(1, email);
			prstmt.setString(2, username);
			prstmt.setString(3, fullname);
			prstmt.setString(4, String.valueOf(gender.getGender()));
			prstmt.setInt(5, department.getId());
			prstmt.setInt(6, position.getId());
			prstmt.setInt(7, id);
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
	public void delete(int id) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			prstmt = con.prepareStatement(DELETE_STRG);
			prstmt.setInt(1, id);
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

}
