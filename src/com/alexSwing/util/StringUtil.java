package com.alexSwing.util;

/**
 * String Util
 * @author liu.zehu
 *
 */
public class StringUtil {
	/**
	 * Determine whether string is empty
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().equals(""));
	}
	
	/**
	 * Determine whether string is not empty
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null && !str.trim().equals(""));
	}
	
	
	
	
}
