package com.nadeem.app.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.nadeem.app.facebook.FacebookToken;
import com.nadeem.app.model.User;

public class FacebookSession extends WebSession {

	private static final long serialVersionUID = 1L;

	private User user;
	private FacebookToken facebookToken;

	public FacebookSession(final Request request) {
		super(request);
	}

	public final boolean isSessionValid() {
		return this.facebookToken != null;
	}

	public final User getUser() {
		return this.user;
	}

	public final void setUser(final User user) {
		this.user = user;
	}
	
	public FacebookToken getFacebookToken() {
		return facebookToken;
	}

	public void setFacebookToken(FacebookToken facebookToken) {
		this.facebookToken = facebookToken;
	}
}
