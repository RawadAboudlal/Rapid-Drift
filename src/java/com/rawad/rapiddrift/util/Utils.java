package com.rawad.rapiddrift.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * @author Rawad
 *
 */
public class Utils {
	
	public static final String NL = "\n";
	public static final String FILE_SEPARATOR = "/";
	
	private static final String EXTENSION_SEPARATOR = ".";
	
	public static String getPath(String... pathParts) {
		return String.join(File.separator, pathParts);
	}
	
	public static String getFilePath(String extension, String... pathParts) {
		return Utils.getPath(pathParts) + EXTENSION_SEPARATOR + extension;
	}
	
	public static URL getResource(String extension, String... pathParts) {
		return Utils.class.getClassLoader().getResource(Utils.getFilePath(extension, pathParts));
	}
	
	public static InputStream openFileStream(String extension, String... pathParts) {
		return Utils.class.getClassLoader().getResourceAsStream(Utils.getFilePath(extension, pathParts));
	}
	
	public static URL getResource(Class<?> clazz, String extension, String... pathParts) {
		return clazz.getResource(Utils.getFilePath(extension, pathParts));
	}
	
	public static InputStream openFileStream(Class<?> clazz, String extension, String... path) {
		return clazz.getResourceAsStream(Utils.getFilePath(extension, path));
	}
	
	public static int parseInt(String potentialInt) {
		
		try {
			return Integer.parseInt(potentialInt);
		} catch(Exception ex) {
			return 0;
		}
		
	}
	
	public static double parseDouble(String potentialDouble) {
		
		try {
			return Double.parseDouble(potentialDouble);
		} catch(Exception ex) {
			return 0d;
		}
		
	}
	
	public static float parseFloat(String potentialFloat) {
		
		try {
			return Float.parseFloat(potentialFloat);
		} catch(Exception ex) {
			return 0f;
		}
		
	}
	
}
