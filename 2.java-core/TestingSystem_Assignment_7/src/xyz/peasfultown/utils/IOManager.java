package xyz.peasfultown.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IOManager {
	private static final String FILE_NOT_FOUND_EXCEPTION_MSG = "File not found";
	private static final String IO_EXCEPTION_MSG = "IO Exception";
	
	public static String readFile(String filepath) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(filepath))) {
			String l;
			while ( (l = buffReader.readLine()) != null) {
				sb.append(l).append('\n');
			}
		} catch (FileNotFoundException fnfe) {
			System.err.println(FILE_NOT_FOUND_EXCEPTION_MSG);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
		return sb.toString();
	}
	
	public static void writeFile(String filepath, boolean appendMode, String content) {
		
	}
}
