package net.wrobeseb.sqlbuilder.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Klasa narzedziowa I/O
 * 
 * @author wrobese2
 */
public final class IOUtils {

	/**
	 * Sciezka do katalogu glownego aplikacji
	 */
	public static final String CLASSPATH = System.getProperty("java.class.path").split(";")[0]; 

	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	
	public static String createFile(byte[] bytes, String fileName) throws IOException {
		
		String absoluteFilePath = TEMP_DIR + fileName;
		
		FileOutputStream ostream = new FileOutputStream(absoluteFilePath, false);
		try {
	        ostream.write(bytes, 0, bytes.length);
	    } finally {
	      ostream.close();
	    }
		
		return absoluteFilePath;
	}
	
	public static boolean deleteFile(String fileName) {
		return new File(TEMP_DIR + fileName).delete();
	}
	
	/**
	 * Konstruktor prywatny uniemozliwiajacy utworzenie objektu
	 */
	private IOUtils() {}
}
