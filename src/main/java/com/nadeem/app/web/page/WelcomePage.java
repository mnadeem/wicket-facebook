package com.nadeem.app.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;

public class WelcomePage extends BasePage {

	private static final long serialVersionUID = 1L;

    public WelcomePage(final PageParameters parameters) {
    	super(parameters);
    	
    	if (getLoggedInUser() == null) {
    		setResponsePage(LoginPage.class);
		} else {
			add(new Label("facebookUser", getLoggedInUser().getFirstName()));
		}
    	
    }
}
