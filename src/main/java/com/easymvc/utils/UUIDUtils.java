package com.easymvc.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UUIDUtils {

	public static String getUUID() {
		String timestamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String mid = "";
		try {
			mid = md5Encode(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mid;
	}
	
	/***
	 * MD5加密 生成32位md5码
	 * 
	 * @param 待加密字符串
	 * @return 返回32位md5码
	 */
	public static String md5Encode(String inStr) throws Exception {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = inStr.getBytes("UTF-8");
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}

	public static String debug_key(String mid, String page_id, String sub_page_id,int action) {
		String timestamp= new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		String value = mid + page_id + sub_page_id +action+ timestamp;
		String md5 = "";
		try {
			md5 = md5Encode(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5;
	}
	
	public static int genUserBucketKey(String mid) {
		String ret = "";
		try {
			ret = UUIDUtils.md5Encode(mid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int ml = ret.length() / 2;

		String pr = ret.substring(0, ml);
		String lt = ret.substring(ml);

		BigInteger bis = new BigInteger(pr, 16);
		BigInteger bie = new BigInteger(lt, 16);
		BigInteger base = new BigInteger("120");

		int bucket_id = bis.add(bie).mod(base).intValue() + 1;
		
		return bucket_id;
	}
	
}
