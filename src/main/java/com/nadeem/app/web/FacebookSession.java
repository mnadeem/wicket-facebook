package com.nadeem.app.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.nadeem.app.facebook.FacebookClient;
import com.nadeem.app.model.User;

public class FacebookSession extends WebSession {

	private static final long serialVersionUID = 1L;

	private User user;
	private FacebookClient facebookClient;

	public FacebookSession(final Request request) {
		super(request);
	}

	public final boolean isSessionValid() {
		return this.facebookClient != null;
	}

	public final User getUser() {
		return this.user;
	}

	public final void setUser(final User user) {
		this.user = user;
	}

	public final FacebookClient getFacebookClient() {
		return this.facebookClient;
	}

	public final void setFacebookClient(final FacebookClient facebookClient) {
		this.facebookClient = facebookClient;
	}
}
