package xyz.peasfultown.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
	private static final String FILE_NOT_FOUND_EXCPT = "Cannot find properties file";
	public static Connection getConnection() throws SQLException {
		try (FileReader fr = new FileReader(Paths.get("./src/resources/database.properties").toFile())){
			Properties props = new Properties();
			props.load(fr);
			Connection con = DriverManager.getConnection(props.getProperty("url"), props.getProperty("user"), props.getProperty("pass"));			
			return con;
		} catch (FileNotFoundException fnfe) {
			System.err.println(FILE_NOT_FOUND_EXCPT);
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			throw new SQLException(sqle);
		}
		return null;
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
}
