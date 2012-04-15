package com.nadeem.app.exception;

public enum ErrorType {
	
	AUTHERROR(),OTHER();
	
	public static ErrorType getErrorType (String typeCode) {
		if ("OAuthException".equalsIgnoreCase(typeCode)) {
			return AUTHERROR;
		}
		return OTHER;
	}
}
