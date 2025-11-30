package xyz.peasfultown.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.NotDirectoryException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
	
	public static void deleteFile(String path) {
		try {
			Files.delete(Paths.get(path));
		} catch (NoSuchFileException nsfe) {
			System.err.println(nsfe.getMessage());
		} catch (DirectoryNotEmptyException dnee) {
			System.err.println(dnee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static boolean isDirectory(String path) {
		return Files.isDirectory(Paths.get(path));
	}
	
	public static List<String> getAllFileName(String path) {
		List<String> filenames = new ArrayList<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path))) {
			for (Path p : stream) {
				filenames.add(p.getFileName().toString());
			}
		} catch (NotDirectoryException nde) {
			System.err.println(nde.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		
		return filenames;
	}
	
	public static void copyFile(String source, String target, String newName) {
		try {
			if (!isFileExists(source))
				throw new FileNotFoundException();
			Files.copy(Paths.get(source), Paths.get(target).resolve(newName));
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (FileAlreadyExistsException faee) {
			System.err.println(faee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void copyFile(String source, String target) {
		try {
			Path orig = Paths.get(source);
			if (!isFileExists(source))
				throw new FileNotFoundException();
			Files.copy(orig, Paths.get(target).resolve(orig.getFileName()));
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (FileAlreadyExistsException faee) {
			System.err.println(faee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void moveFile(String source, String target) {
		try {
			if (!isFileExists(source))
				throw new FileNotFoundException();
			Path orig = Paths.get(source);
			Files.move(orig, Paths.get(target).resolve(orig.getFileName()));
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (FileAlreadyExistsException faee) {
			System.err.println(faee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void renameFile(String source, String targetName) {
		try {
			if (!isFileExists(source))
				throw new FileNotFoundException();
			Path orig = Paths.get(source);
			Files.move(orig, orig.resolveSibling(targetName));
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		} catch (FileAlreadyExistsException faee) {
			System.err.println(faee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void createDirectory(String path) {
		try {
			Files.createDirectory(Paths.get(path));
		} catch (FileAlreadyExistsException faee) {
			System.err.println(faee.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
	
	public static void downloadFile(String link, String target) {

	}
}
