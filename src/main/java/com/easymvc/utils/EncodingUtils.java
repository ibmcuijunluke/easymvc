package com.easymvc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EncodingUtils {
	
	static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

	public static String convertUnicode(String str) {
		if (str == null || str == "") {
			throw new RuntimeException(" Convert Unicode String is null");
		}
		Matcher matcher = reUnicode.matcher(str);
		StringBuffer sb = new StringBuffer(str.length());
		while (matcher.find()) {
			matcher.appendReplacement(sb, Character.toString((char) Integer.parseInt(matcher.group(1), 16)));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
