package com.nadeem.app.web;

import org.apache.wicket.Request;

import com.nadeem.app.facebook.FacebookParameterProvider;

public class WicketFacebookParameterProvider implements FacebookParameterProvider {
	final Request request;
	
	public WicketFacebookParameterProvider(final Request newRequest) {
		this.request = newRequest;
	}

	public String getParameter(final String paramName) {
		return request.getParameter(paramName);
	}

}
