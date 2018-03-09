package com.easymvc.utils;


import java.math.BigInteger;
import java.security.MessageDigest;

public class UserBucket {
	
	public static void main(String[] args) {
		String mid="cc3eca045e6eb8353798f016b7c52a2d";
		int bucketKey=genUserBucketKey(mid);
		System.out.println(bucketKey);
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
	
	/**
	 * 对字符串md5加密
	 *
	 * @param str
	 * @return
	 * @throws Exception 
	 */
	public static String getMD5(String str) throws Exception {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        // 计算md5函数
	        md.update(str.getBytes());
	        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        throw new Exception("MD5加密出现错误");
	    }
	}
}
