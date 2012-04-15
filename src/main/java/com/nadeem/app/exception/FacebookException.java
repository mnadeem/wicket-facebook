package com.nadeem.app.exception;

import com.nadeem.app.model.FacebookError;

public class FacebookException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private FacebookError facebookError;
	private ErrorType errorType;

	public FacebookException(final FacebookError facebookError) {
		super(facebookError.getMessage());
		this.facebookError 	= facebookError;
		this.errorType		= facebookError.getErrorType();
	}

	public FacebookException(ErrorType errorType) {
		this.errorType = errorType;
	}

	public final FacebookError getoFacebookError() {
		return facebookError;
	}

	public final boolean isOAuthException() {
		if (errorType != null && ErrorType.AUTHERROR.equals(errorType)) {
			return true;
		}
		return false;
	}
}
