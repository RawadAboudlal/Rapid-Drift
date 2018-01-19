package com.rawad.rapiddrift.util;

/**
 * @author Rawad
 *
 */
public class Utils {
	
	public static final String NL = "\n";
	public static final String FILE_SEPARATOR = "/";
	
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
