package com.nadeem.app.exception;

import com.nadeem.app.model.FacebookError;

public class FacebookException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private FacebookError facebookError;

	public FacebookException(final FacebookError facebookError) {
		super(facebookError.getMessage());
		this.facebookError = facebookError;
	}

	public final FacebookError getoFacebookError() {
		return facebookError;
	}

	public final boolean isOAuthException() {
		if (facebookError != null && "OAuthException".equalsIgnoreCase(facebookError.getType())) {
			return true;
		}
		return false;
	}
}
