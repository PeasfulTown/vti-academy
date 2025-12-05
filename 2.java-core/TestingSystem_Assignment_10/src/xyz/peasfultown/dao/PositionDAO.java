package xyz.peasfultown.dao;

import java.sql.SQLException;
import java.util.List;

import xyz.peasfultown.entity.Position;

public interface PositionDAO {
	public void create(String name) throws SQLException;

	public List<Position> read() throws SQLException;

	public Position read(int id) throws SQLException;

	public void update(int id, String newName) throws SQLException;

	public void delete(int id) throws SQLException;
}
