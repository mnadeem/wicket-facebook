package com.nadeem.app.web;

import org.apache.wicket.Page;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.nadeem.app.exception.FacebookException;

public class FacebookRequestcycle extends WebRequestCycle {

	public FacebookRequestcycle(final WebApplication application, final WebRequest request, final Response response) {
		super(application, request, response);
	}

	@Override
	public final Page onRuntimeException(final Page page, final RuntimeException e) {
		if (e instanceof FacebookException && ((FacebookException) e).isOAuthException()) {
			throw new RedirectToUrlException(((FacebookApplication) getApplication()).getFacebookService().getAuthorizationUrl());
		} else {
			return super.onRuntimeException(page, e);
		}
	}
}
