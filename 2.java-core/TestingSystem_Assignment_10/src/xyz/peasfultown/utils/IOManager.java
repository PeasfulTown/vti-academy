package xyz.peasfultown.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;

public class IOManager {
	private static final String FILE_NOT_FOUND_EXCEPTION_MSG = "File not found";
	private static final String IO_EXCEPTION_MSG = "IO Exception";
	private static final String CLASS_NOT_FOUND_EXCEPTION_MSG = "Class not found";

	public static String readFile(String filepath) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader buffReader = new BufferedReader(new FileReader(filepath))) {
			String l;
			while ((l = buffReader.readLine()) != null) {
				sb.append(l).append('\n');
			}
		} catch (NoSuchFileException fnfe) {
			System.err.println(FILE_NOT_FOUND_EXCEPTION_MSG);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
		return sb.toString();
	}

	public static void writeFile(String filepath, boolean append, String content) {
		try (BufferedWriter writer = new BufferedWriter(
				Files.newBufferedWriter(Paths.get(filepath), append ? APPEND : TRUNCATE_EXISTING))) {
			writer.write(content);
		} catch (NoSuchFileException fnfe) {
			System.err.println(FILE_NOT_FOUND_EXCEPTION_MSG);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
	}

//	public static void writeFile(String filepath, boolean append, String content) {
//		Set<OpenOption> truncateOption = new HashSet<>();
//		truncateOption.add(TRUNCATE_EXISTING);
//		truncateOption.add(WRITE);
//		
//		Set<OpenOption> appendOption = new HashSet<>();
//		appendOption.add(APPEND);
//		
//		byte[] cbytes = content.getBytes();
//		ByteBuffer bb = ByteBuffer.wrap(cbytes);
//		try (SeekableByteChannel sbc = Files.newByteChannel(Paths.get(filepath), append ? appendOption : truncateOption)) {
//			sbc.write(bb);
//		} catch (IOException ioe) {
//			System.err.println(IO_EXCEPTION_MSG);
//			ioe.printStackTrace();
//		}
//	}
	
	public static void writeObject(Object obj, String path) {
		File file = new File(Paths.get(path).toString());
		try (ObjectOutputStream objout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, true)))) {
			objout.writeObject(obj);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
	}
	
	public static void writeObject(Object obj, String path, String filename) {
		File file = new File(Paths.get(path).resolve(filename).toString());
		try (ObjectOutputStream objout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, true)))) {
			objout.writeObject(obj);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
	}
	
	public static void writeObjects(List<Object> objs, String path) {
		File file = new File(Paths.get(path).toString());
		try (ObjectOutputStream objout = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(file, true)))) {
			
			Iterator<Object> itr = objs.iterator();
			while (itr.hasNext())
				objout.writeObject(itr.next());
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
	}
	
	public static Object readObject(String path) {
		File file = new File(Paths.get(path).toString());
		Object obj = null;
		try (ObjectInputStream obin = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			obj = obin.readObject();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(CLASS_NOT_FOUND_EXCEPTION_MSG);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
		return obj;
	}
	
	public static List<Object> readObjects(String path) {
		File file = new File(Paths.get(path).toString());
		
		List<Object> objects = new ArrayList<>();
		try (ObjectInputStream obin = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream(file)))) {
			while (true) {
				objects.add(obin.readObject());				
			}
		} catch (EOFException eofe) {
			
		} catch (ClassNotFoundException cnfe) {
			System.err.println(CLASS_NOT_FOUND_EXCEPTION_MSG);
		} catch (IOException ioe) {
			System.err.println(IO_EXCEPTION_MSG);
			ioe.printStackTrace();
		}
		return objects;
	}
}
