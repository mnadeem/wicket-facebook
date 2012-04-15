package com.nadeem.app.exception;

public class ErrorHelper {

	public static Throwable getRootCause(final RuntimeException exception) {

		Throwable rootCause = exception.getCause();

		while (exception != null && rootCause.getCause() != null) {
			rootCause = rootCause.getCause();
		}
		return rootCause;
	}

	public static boolean isFacebookAuthException(final Throwable cause) {
		return cause != null && cause instanceof FacebookException && ((FacebookException)cause).isOAuthException();
	}

	public static boolean isRootCauseFacebookAuthException(final RuntimeException exception) {
		Throwable cause = ErrorHelper.getRootCause(exception);
		return cause != null && cause instanceof FacebookException && ((FacebookException)cause).isOAuthException();
	}
}
