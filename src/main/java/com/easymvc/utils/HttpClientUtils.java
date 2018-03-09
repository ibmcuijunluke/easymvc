package com.easymvc.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 网络请求工具
 *
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {
	
	private static HttpClient httpClient = null;
	private static HttpGet httpGet;
	private static HttpPost httpPost;
	public static String TIME_OUT = "请求超时";
	public static String SERVER_ERROR = "服务器异常";
	public static String NETWORK_ERROR = "网络异常";

	static {
		httpClient = getHttpClient();
	}
	
	public static RespMsg sendGetRequest(String url)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, null, "UTF-8");
	}
	
	public static RespMsg sendGetRequestGB2312(String url)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, null, "GB2312");
	}

	public static RespMsg sendGetRequestGBK(String url)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, null, "GBK");
	}
	
	public static RespMsg sendGetRequest(String url, Map<String, Object> params)
			throws ClientProtocolException, IOException {
		return sendGetRequest(url, params, "UTF-8");
	}

	/**
	 * Http Get请求 请求地址
	 *
	 * @param url
	 *            Get参数
	 * @param params
	 *            编码
	 * @param encode
	 *            返回请求结果
	 * @return
	 * @throws org.apache.http.client.ClientProtocolException
	 * @throws java.io.IOException
	 */
	public static RespMsg sendGetRequest(String url, Map<String, Object> params, String encode) {
		String content = null;
		RespMsg respMsg= null;
		String userAgent="Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.110 Mobile Safari/537.36";
		try {
			/*HttpHost proxy = new HttpHost("112.83.240.113", 8888);  
	        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);  
	        httpClient = HttpClients.custom().setRoutePlanner(routePlanner).build(); */ 
			if (null == params) {
				httpGet = new HttpGet(url);
				httpGet.setHeader("User-Agent", userAgent);
			} else {
				url=url+ dealGetParams(params, encode);
				httpGet = new HttpGet(url);
				httpGet.setHeader("User-Agent", userAgent);
			}
			HttpResponse response = httpClient.execute(httpGet);
			int code=response.getStatusLine().getStatusCode();
			String resHeader = null;
			
			org.apache.http.Header[] header = response.getHeaders("Content-Length");
			for (int i = 0; i < header.length; i++) {
				if (header.length != 0){
					resHeader = header[0].toString().split(": ")[1];
	            }
			}
			
			if ( code == HttpStatus.SC_OK) {
				content = EntityUtils.toString(response.getEntity(),encode);
				response.getEntity().getContentLength();
			}
			respMsg=new RespMsg(code, url, content, resHeader);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpGet.releaseConnection();
		return respMsg;
	}
	
	
	/**
	 * POST请求 返回请求结果 HashMap键值参数
	 *
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked" })
	public static String sendPostRequest(String url, Object params, String encode) throws Exception {
		String resultStr = null;
		httpPost = new HttpPost(url);
		if (params != null) {
			StringEntity entity;
			if (params instanceof Map) {
				entity = new StringEntity(dealPostParams((HashMap<String, String>) params, encode));
			} else if (params instanceof String) {
				entity = new StringEntity((String) params, encode);
			} else if (params instanceof List) {
				entity = new UrlEncodedFormEntity((List<? extends NameValuePair>) params, encode);
			}else if (params instanceof JSONObject) {
				entity = new StringEntity(params.toString(), encode);
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
			}else {
				throw new Exception("参数有误!");
			}
			httpPost.setEntity(entity);
		}
		HttpResponse response=null;
		try {
				response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				resultStr = EntityUtils.toString(response.getEntity());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		httpPost.releaseConnection();
		return resultStr != null ? resultStr : null;
	}

	public static String sendPostRequest(String url) throws Exception {
		return sendPostRequest(url, "");
	}

	/**
	 * 键值对请求 默认UTF-8编码
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, Map<String, String> params) throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}
	
	public static String sendPostRequest(String url, JSONObject params) throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}

	/**
	 * String 默认UTF-8编码
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, String params) throws Exception {
		return sendPostRequest(url, params == null ? null : params, "UTF-8");
	}

	/**
	 * 键值对请求 默认UTF-8编码
	 *
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String sendPostRequest(String url, List<NameValuePair> params) throws Exception {
		return sendPostRequest(url, params, "UTF-8");
	}

	/**
	 * 处理Get方式请求的URL
	 *
	 * @param params
	 * @param enc
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	private static String dealGetParams(Map<String, Object> params, String enc) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append("?");
		for (Entry<String, Object> entry : params.entrySet()) {
			// 参数名=参数&参数名=参数
			sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue().toString(), enc)).append("&");
		}
		// 删除最后一个&
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}
//	private static String dealGetParams(Map<String, String> params, String enc) throws UnsupportedEncodingException {
//		StringBuffer sb = new StringBuffer();
//		sb.append("?");
//		for (Entry<String, String> entry : params.entrySet()) {
//			// 参数名=参数&参数名=参数
//			sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), enc)).append("&");
//		}
//		// 删除最后一个&
//		sb.deleteCharAt(sb.length() - 1);
//		return sb.toString();
//	}

	/**
	 * 处理POST请求URL
	 *
	 * @param params
	 * @param enc
	 * @return
	 */
	private static String dealPostParams(Map<String, String> params, String enc) {
		StringBuffer sb = new StringBuffer();
		for (Entry<String, String> entry : params.entrySet()) {
			try {
				sb.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), enc)).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public static JSONObject doPost(String url, JSONObject json) {
		@SuppressWarnings("resource")
		CloseableHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		JSONObject response = null;
		try {
			StringEntity stringEntity = new StringEntity(json.toString());
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			post.setEntity(stringEntity);
			HttpResponse res = client.execute(post);
			String result ="";
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				result = EntityUtils.toString(entity);
				response = JSONObject.fromObject(result);
			} else {
				System.out.println(result);
				System.out.println("statusCode==" + res.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	public static String doPostRequest(String url, JSONObject json) {
		@SuppressWarnings("resource")
		CloseableHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		String response = null;
		try {
			StringEntity stringEntity = new StringEntity(json.toString());
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			post.setEntity(stringEntity);
			HttpResponse res = client.execute(post);
			if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = res.getEntity();
				String result = EntityUtils.toString(entity);
				response = JSONObject.fromObject(result).toString();
			} else {
				System.out.println("statusCode==" + res.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return response;
	}
	
	/**
	 * 获取HttpClient
	 *
	 * @return
	 */
	public static synchronized HttpClient getHttpClient() {
		if (null == httpClient) {
			httpClient = new DefaultHttpClient();
		}
		return httpClient;
	}
	
}