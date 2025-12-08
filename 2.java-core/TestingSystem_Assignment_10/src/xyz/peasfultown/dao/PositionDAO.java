package xyz.peasfultown.dao;

import java.util.List;

import xyz.peasfultown.entity.DatabaseException;
import xyz.peasfultown.entity.Position;

public interface PositionDAO {
	public void create(String name) throws DatabaseException;

	public List<Position> read() throws DatabaseException;

	public Position read(int id) throws DatabaseException;

	public void update(int id, String newName) throws DatabaseException;

	public void delete(int id) throws DatabaseException;
}
