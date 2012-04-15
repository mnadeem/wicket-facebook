package com.nadeem.app.exception;

public class ErrorHelper {
	
	public static Throwable getRootCause(RuntimeException exception) {
		
		Throwable rootCause = exception.getCause();
		
		while (exception != null && rootCause.getCause() != null) {
			rootCause = rootCause.getCause();
		}
		return rootCause;		
	}
	
	public static boolean isFacebookAuthException(Throwable cause) {
		return cause != null && cause instanceof FacebookException && ((FacebookException)cause).isOAuthException();
	}
	
	public static boolean isRootCauseFacebookAuthException(RuntimeException exception) {
		Throwable cause = ErrorHelper.getRootCause(exception);
		return cause != null && cause instanceof FacebookException && ((FacebookException)cause).isOAuthException();
	}
}
