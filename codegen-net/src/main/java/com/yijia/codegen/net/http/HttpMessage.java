package com.yijia.codegen.net.http;

import java.util.HashMap;
import java.util.Map;

public class HttpMessage {

	private String method;
	private String requestURL;
	private String protocol;

	private Map<String, Object> headers = new HashMap<String, Object>();
	private Map<String, Object> cookie = new HashMap<String, Object>();
	private Object body;

	public HttpMessage(String requestLine) {
		if(null == requestLine || "".equals(requestLine))
			throw new IllegalAccessError("无效的请求");
		String[] args = requestLine.split(" ");
		this.method = args[0].toLowerCase();
		this.requestURL = args[1];
		this.protocol = args[2];
	}

	public void readHead(String head) {
		String[] kv = head.split("\\:");
		if("".equals(kv[0].trim())) {
			addCookie(kv[1].trim());
		} else {
			addHeader(kv[0].trim(), kv[1].trim());
		}
	}

	public void readBody(String body) {

	}

	private void addHeader(String key, Object value) {
		// TODO Auto-generated method stub

	}
	
	private void addCookie(String info) {
		// TODO Auto-generated method stub

	}

	public String getMethod() {
		return method;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public String getProtocol() {
		return protocol;
	}

	public Map<String, Object> getHeaders() {
		return headers;
	}

	public Map<String, Object> getCookie() {
		return cookie;
	}

	public Object getBody() {
		return body;
	}

}
