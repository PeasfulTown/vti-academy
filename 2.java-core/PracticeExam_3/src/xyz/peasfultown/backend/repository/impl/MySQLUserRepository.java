package xyz.peasfultown.backend.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import xyz.peasfultown.backend.repository.IUserRepository;
import xyz.peasfultown.backend.repository.exception.DatabaseException;
import xyz.peasfultown.entity.Admin;
import xyz.peasfultown.entity.Employee;
import xyz.peasfultown.entity.Skill;
import xyz.peasfultown.entity.User;
import xyz.peasfultown.entity.UserType;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLUserRepository implements IUserRepository {
	// EXCEPTIONS
	private final String CREATE_EXCPT = "Unable to create admin account";
	private final String AUTH_USER_HASH_EXCPT = "Unable to authenticare user, unable to hash user password";
	private final String AUTH_INCORRECT_PASSWORD_EXCPT = "Unable to authenticare user, password not matching user in database";
	private final String USER_TYPE_EXCPT = "Unable to log in to account, unable to determine type of user";
	private final String DELETE_USER_EXCPT = "Unable to delete user";

	// MYSQL
	private final String CREATE_USER_STRG = "INSERT INTO `user` (fullname, email, pass, `type`) VALUE (?, ?, ?, ?)";
	private final String CREATE_EMPLOYEE_STRG = "INSERT INTO employee (employee_id, skill_id) VALUE (?, ?)";
	private final String CREATE_SKILL_STRG = "INSERT INTO skill (`name`) VALUE (?)";
	
	private final String DELETE_USER_STRG = "DELETE FROM `user` WHERE id = ?;";

	private final String READ_USER_STRG = "SELECT u.id, u.fullname, u.email, u.pass, u.`type`, "
			+ "IF(u.`type` = \"admin\", a.years_of_experience, null) AS years_of_experience, "
			+ "IF (u.`type` = \"employee\", s.id, null) AS skill_id, "
			+ "IF (u.`type` = \"employee\", s.`name`, null) AS skill_name "
			+ "FROM `user` u "
			+ "LEFT JOIN `admin` a ON u.id = a.admin_id AND u.`type` = \"admin\" "
			+ "LEFT JOIN employee e ON u.id = e.employee_id AND u.`type` = \"employee\" "
			+ "LEFT JOIN skill s ON e.skill_id = s.id AND u.`type` = \"employee\";";
	private final String READ_USER_BY_ID_STRG = "SELECT u.id, u.fullname, u.email, u.pass, u.`type`, "
			+ "IF(u.`type` = \"admin\", a.years_of_experience, null) AS years_of_experience, "
			+ "IF (u.`type` = \"employee\", s.id, null) AS skill_id, "
			+ "IF (u.`type` = \"employee\", s.`name`, null) AS skill_name "
			+ "FROM `user` u "
			+ "LEFT JOIN `admin` a ON u.id = a.admin_id AND u.`type` = \"admin\" "
			+ "LEFT JOIN employee e ON u.id = e.employee_id AND u.`type` = \"employee\" "
			+ "LEFT JOIN skill s ON e.skill_id = s.id AND u.`type` = \"employee\" WHERE u.id = ?;";
	private final String READ_USER_BY_EMAIL_STRG = "SELECT u.id, u.fullname, u.email, u.pass, u.`type`, "
			+ "IF(u.`type` = \"admin\", a.years_of_experience, null) AS years_of_experience, "
			+ "IF (u.`type` = \"employee\", s.id, null) AS skill_id, "
			+ "IF (u.`type` = \"employee\", s.`name`, null) AS skill_name "
			+ "FROM `user` u "
			+ "LEFT JOIN `admin` a ON u.id = a.admin_id AND u.`type` = \"admin\" "
			+ "LEFT JOIN employee e ON u.id = e.employee_id AND u.`type` = \"employee\" "
			+ "LEFT JOIN skill s ON e.skill_id = s.id AND u.`type` = \"employee\" WHERE u.email = ?;";
	private final String READ_SKILL_BY_NAME_STRG = "SELECT id, `name` FROM skill WHERE `name` = ?;";
	

	@Override
	public void createEmployee(String fullname, String email, String pass, String skill) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			prstmt = con.prepareStatement(CREATE_USER_STRG, Statement.RETURN_GENERATED_KEYS);
			prstmt.setString(1, fullname);
			prstmt.setString(2, email);
			prstmt.setString(3, pass);
			prstmt.setString(4, UserType.EMPLOYEE.toString());
			prstmt.executeUpdate();
			rs = prstmt.getGeneratedKeys();
						
			Skill sk = readSkill(con, skill);
			if (sk == null)
				sk = createSkill(con, skill);

			if (rs.next()) {
				int genId = rs.getInt(1);
				prstmt = con.prepareStatement(CREATE_EMPLOYEE_STRG);
				prstmt.setInt(1, genId);
				prstmt.setShort(2, sk.getId());
				prstmt.executeUpdate();
				con.commit();
			} else {
				con.rollback();
				throw new DatabaseException(CREATE_EXCPT);
			}
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
			JDBCUtils.close(rs);
		}
	}

	@Override
	public List<User> read() throws DatabaseException {
		List<User> users = new ArrayList<>();
		try (Connection con = JDBCUtils.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(READ_USER_STRG)) {
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String pass = rs.getString(4);
				String type = rs.getString(5);
				if (type.equalsIgnoreCase(UserType.ADMIN.name())) {
					short yoe = rs.getShort(6);
					Admin admin = new Admin(id, name, email, pass, yoe);
					users.add(admin);
				} else {
					Skill sk = new Skill(rs.getShort(7), rs.getString(8));
					Employee empl = new Employee(id, name, email, pass, sk);
					users.add(empl);
				}
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
		return users;
	}

	@Override
	public User read(int id) throws DatabaseException {
		ResultSet rs = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_USER_BY_ID_STRG)) {
			prstmt.setInt(1, id);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				String fullname = rs.getString(1);
				String email = rs.getString(2);
				String pass = rs.getString(3);
				String type = rs.getString(4);
				if (type.equalsIgnoreCase(UserType.ADMIN.name())) {
					short yoe = rs.getShort(5);
					return new Admin(id, fullname, email, pass, yoe);
				} else {
					Skill skill = new Skill(rs.getShort(6), rs.getString(7));
					return new Employee(id, fullname, email, pass, skill);
				}
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return null;
	}
	
	@Override
	public User read(String email) throws DatabaseException {
		ResultSet rs = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_USER_BY_EMAIL_STRG)) {
			prstmt.setString(1, email);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String pass = rs.getString(4);
				String type = rs.getString(5);
				if (type.equalsIgnoreCase(UserType.ADMIN.name())) {
					short yoe = rs.getShort(6);
					return new Admin(id, fullname, email, pass, yoe);
				} else {
					Skill skill = new Skill(rs.getShort(7), rs.getString(8));
					return new Employee(id, fullname, email, pass, skill);
				}
			} else {
				return null;	
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
	}
	
	@Override
	public boolean isAdmin(String email) throws DatabaseException {
		ResultSet rs = null;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement prstmt = con.prepareStatement(READ_USER_BY_EMAIL_STRG)) {
			prstmt.setString(1, email);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString("type").equalsIgnoreCase(UserType.ADMIN.name()))
					return true;
				else
					return false;
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(rs);
		}
		return false;
	}

	@Override
	public void delete(int id) throws DatabaseException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(DELETE_USER_STRG);
			prstmt.setInt(1, id);
			prstmt.executeUpdate();
			
			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new DatabaseException(DELETE_USER_EXCPT, sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

	private Skill readSkill(Connection con, String skillName) throws DatabaseException {
		try (PreparedStatement prstmt = con.prepareStatement(READ_SKILL_BY_NAME_STRG)) {
			prstmt.setString(1, skillName);
			try (ResultSet rs = prstmt.executeQuery()) {
				if (rs.next())
					return new Skill(rs.getShort(1), rs.getString(2));
				else
					return null;
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
	
	private Skill createSkill(Connection con, String skillName) throws DatabaseException {
		try (PreparedStatement prstmt = con.prepareStatement(CREATE_SKILL_STRG, Statement.RETURN_GENERATED_KEYS)) {
			prstmt.setString(1, skillName);
			prstmt.executeUpdate();
			try (ResultSet rs = prstmt.getGeneratedKeys()) {
				if (rs.next())
					return new Skill(rs.getShort(1), skillName);
				else 
					return null;
			}
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
}
