package com.nadeem.app.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.nadeem.app.model.User;
import com.nadeem.app.util.FacebookClient;

public class FacebookSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private User user;
	private FacebookClient facebookClient;

	public FacebookSession(Request request) {
		super(request);
	}
	
	public boolean isSessionValid() {
		return facebookClient != null;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public FacebookClient getFacebookClient() {
		return facebookClient;
	}


	public void setFacebookClient(FacebookClient facebookClient) {
		this.facebookClient = facebookClient;
	}

}
