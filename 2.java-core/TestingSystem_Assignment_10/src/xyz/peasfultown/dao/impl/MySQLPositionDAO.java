package xyz.peasfultown.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xyz.peasfultown.dao.PositionDAO;
import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Position;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLPositionDAO implements PositionDAO {
	private static final String CREATE_STRG = "INSERT INTO position (position_name) VALUE (?)";
	private static final String READ_ALL_STRG = "SELECT * FROM position";
	private static final String READ_BY_ID_STRG = "SELECT * FROM position WHERE position_id = ?";
	private static final String UPDATE_STRG = "UPDATE position SET position_name = ? WHERE position_id = ?";
	private static final String DELETE_STRG = "DELETE FROM position WHERE position_id = ?";

	@Override
	public void create(String name) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			
			prstmt = con.prepareStatement(CREATE_STRG);
			prstmt.setString(1, name);
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
	public List<Position> read() throws DatabaseException {
		List<Position> positions = new ArrayList<>();
		try (Connection con = JDBCUtils.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(READ_ALL_STRG)) {
			while (rs.next()) {
				Position pos = new Position(rs.getInt(1), rs.getString(2));
				positions.add(pos);
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
		return positions;
	}

	@Override
	public Position read(int id) throws DatabaseException {
		ResultSet rs = null;
		Position pos = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_BY_ID_STRG)) {
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			pos = new Position(rs.getInt(1), rs.getString(2));
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return pos;
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
