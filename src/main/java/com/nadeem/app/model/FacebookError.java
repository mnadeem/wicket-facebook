package com.nadeem.app.model;

import java.io.Serializable;

import com.nadeem.app.exception.ErrorType;

public class FacebookError implements Serializable {

	private static final long serialVersionUID = 1L;
	private String code;
	private String type;
	private String message;

	public FacebookError() {

	}

	public FacebookError(final String type) {
		this("", type, "");
	}

	public FacebookError(final String code, final String type, final String message) {
		this.code = code;
		this.type = type;
		this.message = message;
	}

	public ErrorType getErrorType() {
		return ErrorType.getErrorType(type);
	}
	public final String getCode() {
		return code;
	}
	public final void setCode(final String code) {
		this.code = code;
	}
	public final String getType() {
		return type;
	}
	public final void setType(final String type) {
		this.type = type;
	}
	public final String getMessage() {
		return message;
	}
	public final void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public final String toString() {
		return String.format("FacebookError[code=%s , type=%s, message=%s ]", code, type, message);
	}
}
