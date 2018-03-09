package com.easymvc.utils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(String str) {
		if ((str != null) && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static int countMatches(String str, String subStr) {
		if ((isEmpty(str)) || (isEmpty(subStr))) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(subStr, idx)) != -1) {
			count++;
			idx += subStr.length();
		}
		return count;
	}
}
