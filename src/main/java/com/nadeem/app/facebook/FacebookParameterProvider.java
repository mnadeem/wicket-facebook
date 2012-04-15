package com.nadeem.app.facebook;

public interface FacebookParameterProvider {
	String AUTH_CODE		= "code";
	String SIGNED_REQUEST	= "signed_request";

	String getParameter(final String paramName);
}
