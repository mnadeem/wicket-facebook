package com.nadeem.app.web;

import org.apache.wicket.Page;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.Response;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

import com.nadeem.app.exception.OAuthException;
import com.nadeem.app.util.FacebookClient;

public class FacebookRequestcycle extends WebRequestCycle {

	public FacebookRequestcycle(WebApplication application, WebRequest request, Response response) {
		super(application, request, response);
	}
	
	@Override
	public Page onRuntimeException(Page page, RuntimeException e) {
		if (e instanceof OAuthException && ((OAuthException) e).isOAuthException()) {
			throw new RedirectToUrlException(((FacebookApplication)getApplication()).getOauthService().getAuthorizationUrl(FacebookClient.EMPTY_TOKEN));
		} else {
			return super.onRuntimeException(page, e);
		}
	}
}
