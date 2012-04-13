package com.nadeem.app.web;

import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.page.AbstractPageAuthorizationStrategy;

public class FacebookPageAuthorizationStrategy extends AbstractPageAuthorizationStrategy {
	
	@Override
	protected <T extends Page> boolean isPageAuthorized(final Class<T> pageClass) {

		if (((FacebookSession)Session.get()).isSessionValid()) {
			return true;
		}

		return false;
	}
}
