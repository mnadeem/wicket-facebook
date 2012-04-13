package com.nadeem.app.exception;

import com.nadeem.app.model.OAuthError;

public class OAuthException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private OAuthError oAuthError;

	public OAuthException(final OAuthError oAuthError) {
		super(oAuthError.getMessage());
		this.oAuthError = oAuthError;
	}

	public final OAuthError getoAuthError() {
		return oAuthError;
	}

	public final void setoAuthError(final OAuthError oAuthError) {
		this.oAuthError = oAuthError;
	}

	public final boolean isOAuthException() {
		if (oAuthError != null && "OAuthException".equalsIgnoreCase(oAuthError.getType())) {
			return true;
		}
		return false;
	}

}
