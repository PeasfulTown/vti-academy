package xyz.peasfultown.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import xyz.peasfultown.dao.GroupDAO;
import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Department;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.entity.Gender;
import xyz.peasfultown.entity.Group;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLGroupDAO implements GroupDAO {
	private static final String CREATE_STRG = "INSERT INTO group (group_name, creator_id) VALUE (?, ?)";
	private static final String READ_ALL_STRG = "SELECT a.account_id, a.email, a.username, a.fullname, a.gender,\n"
			+ "d.department_id, d.department_name, \n"
			+ "p.position_id, p.position_name, \n"
			+ "a.create_date AS a_create_date, \n"
			+ "g.group_id, g.group_name, g.create_date AS g_create_date \n"
			+ "FROM `group` g \n"
			+ "LEFT JOIN `account` a \n"
			+ "ON g.creator_id = a.account_id \n"
			+ "LEFT JOIN department d \n"
			+ "ON a.department_id = d.department_id \n"
			+ "LEFT JOIN `position` p \n"
			+ "ON a.position_id = p.position_id";
	private static final String READ_BY_ID_STRG = "SELECT a.account_id, a.email, a.username, a.fullname, a.gender,\n"
			+ "d.department_id, d.department_name, \n"
			+ "p.position_id, p.position_name, \n"
			+ "a.create_date AS a_create_date, \n"
			+ "g.group_id, g.group_name, g.create_date AS g_create_date \n"
			+ "FROM `group` g \n"
			+ "LEFT JOIN `account` a \n"
			+ "ON g.creator_id = a.account_id \n"
			+ "LEFT JOIN department d \n"
			+ "ON a.department_id = d.department_id \n"
			+ "LEFT JOIN `position` p \n"
			+ "ON a.position_id = p.position_id \n"
			+ "WHERE g.group_id = ?";
	private static final String UPDATE_STRG = "UPDATE `group` SET group_name = ? WHERE group_id = ?";
	private static final String DELETE_STRG = "DELETE FROM `group` WHERE group_id = ?";

	@Override
	public void create(String name, Account creator) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			prstmt = con.prepareStatement(CREATE_STRG);
			prstmt.setString(1, name);
			prstmt.setInt(2, creator.getId());
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
	public List<Group> read() throws DatabaseException {
		List<Group> groups = new ArrayList<>();
		try (Connection con = JDBCUtils.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(READ_ALL_STRG)) {
			while (rs.next()) {
				int cid = rs.getInt("account_id");
				String cemail = rs.getString("email");
				String cusr = rs.getString("username");
				String cname = rs.getString("fullname");
				Gender cg = Gender.getGender(rs.getString("gender").charAt(0));
				Department cdep = new Department(rs.getInt("department_id"), rs.getString("department_name"));
				Position cpos = new Position(rs.getInt("position_id"), rs.getString("position_name"));
				LocalDateTime cdate = rs.getTimestamp("a_create_date").toLocalDateTime();
				Account creator = new Account(cid, cemail, cusr, cname, cg, cdep ,cpos, cdate);
				
				Group g = new Group(rs.getInt("group_id"), rs.getString("group_name"), creator, rs.getTimestamp("g_create_date").toLocalDateTime());
				
				groups.add(g);
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
		return groups;
	}

	@Override
	public Group read(int id) throws DatabaseException {
		ResultSet rs = null;
		Group group = null;
		
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_BY_ID_STRG)){
			prstmt.setInt(1, id);
			
			rs = prstmt.executeQuery();
			if (rs.next()) {
				int cid = rs.getInt("account_id");
				String cemail = rs.getString("email");
				String cusr = rs.getString("username");
				String cname = rs.getString("fullname");
				Gender cg = Gender.getGender(rs.getString("gender").charAt(0));
				Department cdep = new Department(rs.getInt("department_id"), rs.getString("department_name"));
				Position cpos = new Position(rs.getInt("position_id"), rs.getString("position_name"));
				LocalDateTime cdate = rs.getTimestamp("a_create_date").toLocalDateTime();
				Account creator = new Account(cid, cemail, cusr, cname, cg, cdep ,cpos, cdate);
				
				group = new Group(rs.getInt("group_id"), rs.getString("group_name"), creator, rs.getTimestamp("g_create_date").toLocalDateTime());
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return group;
	}

	@Override
	public void update(int id, String newName) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
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
