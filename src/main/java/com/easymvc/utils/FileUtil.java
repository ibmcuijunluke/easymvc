package com.easymvc.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

	static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");

	public static String convertUnicode(String str) {
		if(str==null || str==""){
			throw new RuntimeException(" Convert Unicode String is null") ;
		}
		Matcher matcher = reUnicode.matcher(str);
		StringBuffer sb = new StringBuffer(str.length());
		while (matcher.find()) {
			matcher.appendReplacement(sb, Character.toString((char) Integer.parseInt(matcher.group(1), 16)));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public static String readText2String(File file) {
		char[] buffer = new char[1024];
		StringBuffer stringBuffer = new StringBuffer();
		int count = 0;
		try {
			FileReader inFile = new FileReader(file);
			while (-1 != (count = inFile.read(buffer))) {
				stringBuffer = stringBuffer.append(new String(buffer, 0, count));
			}
			inFile.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return stringBuffer.toString();
	}

	public static void readByBuffer(String filePathName) throws IOException {
		try {
			InputStream in = new FileInputStream(filePathName);
			InputStreamReader inputStreamReader = new InputStreamReader(in);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String replace(String str, String regex, String replacement) {
		if (str == null||str=="") {
			throw new RuntimeException("str is null");
		}
		str.replace(regex, replacement);
		return str;
	}
	
	public static void writeFile(String filePathAndName, String fileContent) {
		File txt = new File(filePathAndName);
		if (!txt.exists()) {
			try {
				txt.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byte bytes[] = new byte[512];
		bytes = fileContent.getBytes();
		int b = fileContent.length();
		try {
			FileOutputStream fos = new FileOutputStream(txt);
			fos.write(bytes, 0, b);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeFiles(String filePathAndName, String fileContent) {
		try {
			File file = new File(filePathAndName);
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), "gb2312");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(String path, String content, String encoding) throws IOException {
		File file = new File(path);
		file.delete();
		file.createNewFile();
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encoding));
		writer.write(content);
		writer.close();
	}
	
	public static void appendMethods(String fileName, String content) {
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(content);
            writer.write(System.getProperty("line.separator", "\n"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public static void fileWriteAppend(String file, String conent){
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"UTF-8"));
			out.write(conent + "\r\n");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void deleteExistFile(String file){
		File files = new File(file);
		if (files.exists()) {
			files.delete();
		}
	}
}
