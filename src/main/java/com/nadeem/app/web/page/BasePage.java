package com.nadeem.app.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;

import com.nadeem.app.facebook.FacebookClient;
import com.nadeem.app.model.User;
import com.nadeem.app.web.FacebookSession;
import com.nadeem.app.web.FacebookApplication;

public class BasePage extends WebPage {
	
	public BasePage(PageParameters parms) {

	}
	
	public User getLoggedInUser() {
		if (getApplicationSession().getUser() == null) {
			getApplicationSession().setUser(getFacebookClient().getLoggedInUser());
		}
		return getApplicationSession().getUser();
	}
	
	public FacebookSession getApplicationSession() {
		return (FacebookSession) getSession();
	}
	
	public FacebookApplication getWebApplication() {
		return (FacebookApplication) super.getApplication();
	}

	public FacebookClient getFacebookClient(){
		return getApplicationSession().getFacebookClient();
	}
}
