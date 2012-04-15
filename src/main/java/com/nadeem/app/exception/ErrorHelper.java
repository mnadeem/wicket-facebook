package com.nadeem.app.exception;

import java.lang.reflect.InvocationTargetException;

public class ErrorHelper {

	public static Throwable getRootCause(final RuntimeException exception) {

		Throwable rootCause = exception.getCause();

		while (exception != null && rootCause != null && rootCause.getCause()!= null) {
			rootCause = rootCause.getCause();
		}
		return rootCause;
	}

	public static boolean isFacebookAuthException(final Throwable cause) {
		return cause != null && cause instanceof FacebookException && ((FacebookException)cause).isOAuthException();
	}

	public static boolean isRootCauseFacebookAuthException(final RuntimeException exception) {

		if (exception instanceof FacebookException && ((FacebookException) exception).isOAuthException()) {
			return true;
		}

		Throwable cause = ErrorHelper.getRootCause(exception);

		if (isFacebookAuthException(cause)) {
			return true;
		}

		if (cause instanceof InvocationTargetException && ((InvocationTargetException) cause).getTargetException() !=null) {
			if (isFacebookAuthException(((InvocationTargetException) cause).getTargetException())) {
				return true;
			}
		}

		return false;
	}
}
