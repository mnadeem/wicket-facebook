package com.nadeem.app.exception;

import com.nadeem.app.model.OAuthError;

public class OAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private OAuthError oAuthError;
	
	public OAuthException(OAuthError oAuthError) {
		super(oAuthError.getMessage());
		this.oAuthError = oAuthError;
	}

	public OAuthError getoAuthError() {
		return oAuthError;
	}

	public void setoAuthError(OAuthError oAuthError) {
		this.oAuthError = oAuthError;
	}
	
	public boolean isOAuthException() {
		if (oAuthError != null && "OAuthException".equalsIgnoreCase(oAuthError.getType())) {
			return true;
		}
		return false;
	}

}
