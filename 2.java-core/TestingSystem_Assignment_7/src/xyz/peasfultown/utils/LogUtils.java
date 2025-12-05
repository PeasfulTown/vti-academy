package xyz.peasfultown.utils;

import java.nio.file.Paths;
import java.time.Instant;

import xyz.peasfultown.entity.MyException;

public class LogUtils {
	private static final String LOG_PATH = 
			Paths.get(System.getProperty("user.home")).resolve("Downloads/logutil").toString();

	public static void writeLog(String message, Throwable cause, StackTraceElement[] stackTrace, Instant timestamp) {
		IOManager.writeObject(new MyException(message, cause, stackTrace, timestamp), LOG_PATH);
	}

	public static void writeLog(MyException excp) {
		IOManager.writeObject(excp, LOG_PATH);
	}
	
	public static String getPath() {
		return LogUtils.LOG_PATH;
	}
}
