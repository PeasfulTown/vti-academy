package xyz.peasfultown.utils;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
	public static boolean isFileExists(String path) {
		return Files.exists(Paths.get(path));
	}

	public static void createNewFile(String path) {
		try {
			Files.createFile(Paths.get(path));
		} catch (FileAlreadyExistsException fae) {
			System.err.println("This file already exists");
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

	public static void createNewFile(String path, String filename) {
		try {
			Files.createFile(Paths.get(path).resolve(filename));
		} catch (FileAlreadyExistsException fae) {
			System.err.println("This file already exists");
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}

}
