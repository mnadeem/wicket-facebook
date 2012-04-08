package com.nadeem.app.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.request.target.basic.RedirectRequestTarget;
import org.scribe.model.Verb;

import com.nadeem.app.model.User;
import com.nadeem.app.util.FacebookClient;

public class LoginPage extends BasePage {
	
	public LoginPage(PageParameters parameters) {
		super(parameters);
		
		String oauthVerifier = parameters.getString("code");
		
		if (getLoggedInUser() != null) {
			setResponsePage(WelcomePage.class);
			
		} else if (oauthVerifier != null) {
			putFacebookUserIntoSession(oauthVerifier);
			setResponsePage(WelcomePage.class);
		}  else if (getLoggedInUser()  == null) {
			RequestCycle.get().setRequestTarget(new RedirectRequestTarget(getAuthService().getAuthorizationUrl(FacebookClient.EMPTY_TOKEN)));
		} else {
			setResponsePage(WelcomePage.class);
		}

	}
	
	private void putFacebookUserIntoSession(String oauthVerifier) {
		FacebookClient facebookClient = new FacebookClient(getAuthService(), oauthVerifier);

		User user  = facebookClient.fetch(Verb.GET, "/me", User.class);
		user.setOauthVerifier(oauthVerifier);

		getApplicationSession().setUser(user);
	}
}
