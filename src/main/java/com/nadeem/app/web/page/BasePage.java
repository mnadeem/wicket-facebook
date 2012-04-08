package com.nadeem.app.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.scribe.oauth.OAuthService;

import com.nadeem.app.model.User;
import com.nadeem.app.web.FacebookSession;
import com.nadeem.app.web.WicketApplication;

public class BasePage extends WebPage {
	
	public BasePage(PageParameters parms) {

	}
	
	public User getLoggedInUser() {
		return getApplicationSession().getUser();
	}
	
	public FacebookSession getApplicationSession() {
		return (FacebookSession) getSession();
	}
	
	public WicketApplication getWebApplication() {
		return (WicketApplication) super.getApplication();
	}
	
	public OAuthService getAuthService() {
		return getWebApplication().getOAuthService();
	}

}
