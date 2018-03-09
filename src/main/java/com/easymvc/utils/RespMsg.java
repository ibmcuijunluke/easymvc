package com.easymvc.utils;

public class RespMsg {

	private int code;
	private String url;
	private String debug_key;
	private String content;
	private String resHeader;

	public RespMsg(int code, String url, String content, String resHeader) {
		super();
		this.code = code;
		this.url = url;
		this.content = content;
		this.resHeader = resHeader;
	}

	public RespMsg(int code, String content) {
		super();
		this.code = code;
		this.content = content;
	}

	public RespMsg(int code, String url, String content) {
		super();
		this.code = code;
		this.url = url;
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDebug_key() {
		return debug_key;
	}

	public void setDebug_key(String debug_key) {
		this.debug_key = debug_key;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getResHeader() {
		return resHeader;
	}

	public void setResHeader(String resHeader) {
		this.resHeader = resHeader;
	}

}
