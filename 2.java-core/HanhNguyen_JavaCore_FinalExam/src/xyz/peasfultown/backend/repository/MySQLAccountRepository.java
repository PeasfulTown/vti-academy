package xyz.peasfultown.backend.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import xyz.peasfultown.entity.Account;
import xyz.peasfultown.entity.Lecturer;
import xyz.peasfultown.entity.Major;
import xyz.peasfultown.entity.Student;
import xyz.peasfultown.utils.JDBCUtils;

public class MySQLAccountRepository implements IAccountRepository {
	private final String GET_ACCOUNT_BY_EMAIL = "SELECT a.account_id, a.fullname, a.email, a.`password`, a.dob, m.major_id, m.`name` AS major_name, a.`type`\r\n"
			+ "FROM `account` a\r\n" + "LEFT JOIN major m\r\n" + "ON a.major_id = m.major_id\r\n"
			+ "WHERE a.email = ?;";
	private final String GET_STUDENTS = "SELECT a.account_id, a.fullname, a.email, a.`password`, a.dob, m.major_id, m.`name` AS major_name, a.`type`\r\n"
			+ "FROM `account` a\r\n" + "LEFT JOIN major m\r\n" + "ON a.major_id = m.major_id\r\n"
			+ "WHERE a.`type` = \"STUDENT\";";
	private final String CREATE_ACCOUNT = "INSERT INTO `account` (fullname, email, `password`, dob, major_id, `type`)\r\n"
			+ "VALUE (?, ?, ?, ?, ?, ?);";
	private final String GET_MAJOR_BY_NAME = "SELECT major_id, `name` FROM major WHERE `name` = ?;";
	private final String CREATE_MAJOR = "INSERT INTO major (`name`)\r\n" + "VALUE (?);\r\n";
	private final String UPDATE_STUDENT_MAJOR = "UPDATE `account` SET major_id = ? WHERE account_id = ?;";
	private final String DELETE_STUDENT_BY_ID = "DELETE FROM `account` WHERE account_id = ? AND `type` = \"STUDENT\";";
	private final String GET_STUDENTS_BY_MAJOR_NAME = "SELECT a.account_id, a.fullname, a.email, a.`password`, a.dob, m.major_id, m.`name` AS major_name, a.`type`\r\n"
			+ "FROM `account` a\r\n" + "LEFT JOIN major m\r\n" + "ON a.major_id = m.major_id\r\n"
			+ "WHERE a.`type` = \"STUDENT\"\r\n" + "AND m.`name` = ?;";
	private final String GET_LECTURERS_BY_MAJOR_NAME = "SELECT a.account_id, a.fullname, a.email, a.`password`, a.dob\r\n"
			+ "FROM `account` a\r\n" + "LEFT JOIN major m\r\n" + "ON a.major_id = m.major_id\r\n"
			+ "WHERE a.`type` = \"LECTURER\"\r\n" + "AND m.`name` = ?;";

	// ONLY TO CHECK IF STUDENT EXISTS
	private final String GET_STUDENT_BY_ID = "SELECT a.account_id, a.`type` FROM `account` a WHERE a.account_id = ? AND a.`type` = \"STUDENT\";";

	@Override
	public boolean studentExists(int studentId) throws RepositoryException {
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			prstmt = con.prepareStatement(GET_STUDENT_BY_ID);
			prstmt.setInt(1, studentId);
			rs = prstmt.executeQuery();
			if (rs.next())
				return true;
			else
				return false;
		} catch (SQLException sqle) {
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con, prstmt, rs);
		}
	}

	@Override
	public Account getAccountByEmail(String email) throws RepositoryException {
		Account acc = null;
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;

		try {
			con = JDBCUtils.getConnection();
			prstmt = con.prepareStatement(GET_ACCOUNT_BY_EMAIL);
			prstmt.setString(1, email);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String password = rs.getString(4);
				LocalDate dob = rs.getDate(5).toLocalDate();
				int majorId = rs.getInt(6);
				String majorName = rs.getString(7);
				Major major = new Major(majorId, majorName);
				String type = rs.getString(8);
				if (type.equalsIgnoreCase("LECTURER")) {
					acc = new Lecturer(id, fullname, email, password, dob, major);
				} else {
					acc = new Student(id, fullname, email, password, dob, major);
				}
			}
		} catch (SQLException sqle) {
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con, prstmt, rs);
		}
		return acc;
	}

	@Override
	public List<Student> getStudents() throws RepositoryException {
		List<Student> students = new ArrayList<>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(GET_STUDENTS);
			while (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				LocalDate dob = rs.getDate(5).toLocalDate();
				int majorId = rs.getInt(6);
				String majorName = rs.getString(7);
				Major major = new Major(majorId, majorName);
				Student student = new Student(id, fullname, email, password, dob, major);
				students.add(student);
			}
		} catch (SQLException sqle) {
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con, stmt, rs);
		}
		return students;
	}

	@Override
	public void createStudent(String fullname, String email, String password, LocalDate dob, String majorName)
			throws RepositoryException {
		Connection con = null;
		PreparedStatement prstmt = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(CREATE_ACCOUNT);

			Major major = getMajorOrCreate(con, majorName);

			prstmt.setString(1, fullname);
			prstmt.setString(2, email);
			prstmt.setString(3, password);
			prstmt.setDate(4, Date.valueOf(dob));
			prstmt.setInt(5, major.getId());
			prstmt.setString(6, "STUDENT");
			prstmt.executeUpdate();

			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

	@Override
	public void createLecturer(String fullname, String email, String password, LocalDate dob, String majorName)
			throws RepositoryException {
		Connection con = null;
		PreparedStatement prstmt = null;

		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(CREATE_ACCOUNT);

			Major major = getMajorOrCreate(con, majorName);

			prstmt.setString(1, fullname);
			prstmt.setString(2, email);
			prstmt.setString(3, password);
			prstmt.setDate(4, Date.valueOf(dob));
			prstmt.setInt(5, major.getId());
			prstmt.setString(6, "Lecturer");
			prstmt.executeUpdate();

			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

	@Override
	public void updateStudentMajor(int studentId, String newMajorName) throws RepositoryException {
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);

			Major major = getMajorOrCreate(con, newMajorName);
			prstmt = con.prepareStatement(UPDATE_STUDENT_MAJOR);
			prstmt.setInt(1, major.getId());
			prstmt.setInt(2, studentId);
			prstmt.executeUpdate();

			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
			JDBCUtils.close(rs);
		}
	}

	@Override
	public void deleteStudent(int studentId) throws RepositoryException {
		Connection con = null;
		PreparedStatement prstmt = null;
		try {
			con = JDBCUtils.getConnection();
			con.setAutoCommit(false);
			prstmt = con.prepareStatement(DELETE_STUDENT_BY_ID);
			prstmt.setInt(1, studentId);
			prstmt.executeUpdate();

			con.commit();
		} catch (SQLException sqle) {
			JDBCUtils.rollback(con);
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con);
			JDBCUtils.close(prstmt);
		}
	}

	@Override
	public List<Student> getStudentByMajor(String majorName) throws RepositoryException {
		List<Student> students = new ArrayList<>();
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();

			Major major = getMajorOrCreate(con, majorName);
			prstmt = con.prepareStatement(GET_STUDENTS_BY_MAJOR_NAME);
			prstmt.setString(1, majorName);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				LocalDate dob = rs.getDate(5).toLocalDate();
				Student student = new Student(id, fullname, email, password, dob, major);
				students.add(student);
			}
		} catch (SQLException sqle) {
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con, prstmt, rs);
		}
		return students;
	}

	@Override
	public List<Lecturer> getLecturersByMajor(String majorName) throws RepositoryException {
		List<Lecturer> lecturers = new ArrayList<>();
		Connection con = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			Major major = getMajorOrCreate(con, majorName);
			prstmt = con.prepareStatement(GET_LECTURERS_BY_MAJOR_NAME);
			prstmt.setString(1, majorName);
			rs = prstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String fullname = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				LocalDate dob = rs.getDate(5).toLocalDate();
				Lecturer l = new Lecturer(id, fullname, email, password, dob, major);
				lecturers.add(l);
			}
		} catch (SQLException sqle) {
			throw new RepositoryException(sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(con, prstmt, rs);
		}
		return lecturers;
	}

	private Major getMajorOrCreate(Connection con, String majorName) throws RepositoryException {
		Major major = null;
		PreparedStatement prstmt = null;
		ResultSet rs = null;
		try {
			prstmt = con.prepareStatement(GET_MAJOR_BY_NAME);
			prstmt.setString(1, majorName);
			rs = prstmt.executeQuery();
			if (rs.next()) {
				major = new Major(rs.getInt(1), rs.getString(2));
			} else {
				prstmt = con.prepareStatement(CREATE_MAJOR, Statement.RETURN_GENERATED_KEYS);
				prstmt.setString(1, majorName);
				prstmt.executeUpdate();
				rs = prstmt.getGeneratedKeys();
				if (rs.next()) {
					major = new Major(rs.getInt(1), majorName);
				}
			}
		} catch (SQLException sqle) {
			throw new RepositoryException("Unable to read/create major: " + sqle.getMessage(), sqle);
		} finally {
			JDBCUtils.close(prstmt, rs);
		}
		return major;
	}
}
