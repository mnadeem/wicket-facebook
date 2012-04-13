package com.nadeem.app.model;

import java.io.Serializable;

public class OAuthError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String code;
	private String type;
	private String message;


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return String.format("OAuthError[%s , %s, %s ]", code, type, message);
	}

}
