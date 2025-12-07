package xyz.peasfultown.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import xyz.peasfultown.entity.DatabaseException;

public class JDBCUtils {
	private static final String FILE_NOT_FOUND_EXCPT = "Cannot find database properties file";
	private static final String PROP_FILE_PATH_STRG = "./src/resources/database.properties";
	
	public static void testConnection() throws DatabaseException {
		Connection con = null;
		try (FileReader fr = new FileReader(Paths.get(PROP_FILE_PATH_STRG).toFile())) {
			Properties props = new Properties();
			props.load(fr);
			con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("pass"));
			System.out.println("Connection successful");
		} catch (FileNotFoundException fnfe) {
			throw new DatabaseException(FILE_NOT_FOUND_EXCPT, fnfe);
		} catch (IOException ioe) {
			throw new DatabaseException(ioe.getMessage(), ioe);
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		} finally {
			close(con);
		}
	}
	
	public static Connection getConnection() throws DatabaseException {
		try (FileReader fr = new FileReader(Paths.get(PROP_FILE_PATH_STRG).toFile())){
			Properties props = new Properties();
			props.load(fr);
			Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("pass"));
			return con;
		} catch (FileNotFoundException fnfe) {
			throw new DatabaseException(fnfe.getMessage(), fnfe);
		} catch (IOException ioe) {
			throw new DatabaseException(ioe.getMessage(), ioe);
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
	
	public static void printSQLException(SQLException sqle) {
		for (Throwable e : sqle) {
			
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.printf("SQLState: %s\n", ((SQLException) e).getSQLState());
				System.err.printf("Error code: %d\n", ((SQLException) e).getErrorCode());
				System.err.printf("Message: %s\n", e.getMessage());

				Throwable t = sqle.getCause();
				while (t != null) {
					System.out.printf("Cause: %s\n", t);
					t = t.getCause();
				}
			} 
		}
	}
	
	public static void rollback(Connection con) throws DatabaseException {
		try {
			if (con != null)
				con.rollback();
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
	
	public static void close(ResultSet rs) throws DatabaseException {
		try {
			if (rs != null) 
				rs.close();
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
	
	public static void close(Statement stmt) throws DatabaseException {
		try {
			if (stmt != null) 
				stmt.close();
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
	
	public static void close(Connection con) throws DatabaseException {
		try {
			if (con != null) {
				if (con.getAutoCommit() == false)
					con.setAutoCommit(true);
				con.close();
			}			
		} catch (SQLException sqle) {
			throw new DatabaseException(sqle.getMessage(), sqle);
		}
	}
}
