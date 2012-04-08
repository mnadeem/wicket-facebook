package com.nadeem.app.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;

import com.nadeem.app.model.User;

public class FacebookSession extends WebSession {

	private static final long serialVersionUID = 1L;
	
	private User user;

	public FacebookSession(Request request) {
		super(request);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

}
