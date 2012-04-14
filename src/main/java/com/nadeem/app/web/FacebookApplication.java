package com.nadeem.app.web;


import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RedirectToUrlException;
import org.apache.wicket.Request;
import org.apache.wicket.RequestCycle;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener;
import org.apache.wicket.authorization.UnauthorizedInstantiationException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebRequest;
import org.scribe.oauth.OAuthService;

import com.nadeem.app.exception.FailedLoginException;
import com.nadeem.app.facebook.FacebookClient;
import com.nadeem.app.service.FacebookServiceConfig;
import com.nadeem.app.service.FacebookServiceProvider;
import com.nadeem.app.web.page.WelcomePage;

public class FacebookApplication extends WebApplication implements IUnauthorizedComponentInstantiationListener {

	private OAuthService facebookService;

	public FacebookApplication() 	{

	}

	@Override
	public final Session newSession(final Request request, final Response response) {
		return new FacebookSession(request);
	}

	@Override
	public final RequestCycle newRequestCycle(final Request request, final Response response) {
		return new FacebookRequestcycle(this, (WebRequest) request, response);
	}

	@Override
	protected final void init() {
		super.init();

		initOauthService();

		getSecuritySettings().setAuthorizationStrategy(new FacebookPageAuthorizationStrategy());
		getSecuritySettings().setUnauthorizedComponentInstantiationListener(this);

		mountFreindlyUrls();
	}

	private void initOauthService() {
		FacebookServiceProvider provider 	= new FacebookServiceProvider(new FacebookServiceConfig(getAppKey(), getSecret(), getCallback()));
		this.facebookService 				= provider.getService();
	}

	private void mountFreindlyUrls() {
		mountBookmarkablePage("/facebook-callback/", WelcomePage.class);
	}

	public final Class<? extends WebPage> getHomePage() {
		return WelcomePage.class;
	}

	public final String getAppKey() {
		return getInitParameter("appKey");
	}

	public final String getSecret() {
		return getInitParameter("secret");
	}

	public final String getCallback() {
		return getInitParameter("callback");
	}

	public final void onUnauthorizedInstantiation(final Component component) {
		if (component instanceof Page) {
			Page page = (Page) component;

			FacebookSession session = (FacebookSession) page.getSession();
			try {
				session.setFacebookClient(getSignedClient(page.getRequest()));


			} catch (FailedLoginException fle) {
				forceLogin(page);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			throw new UnauthorizedInstantiationException(component.getClass());
		}
	}

	private FacebookClient getSignedClient(final Request request) {
		String oauthVerifier = request.getParameter("code");
		if (oauthVerifier != null) {
			return new FacebookClient(this.facebookService, oauthVerifier);
		} else {
			throw new FailedLoginException();
		}
	}

	private void forceLogin(final Page page) {
		throw new RedirectToUrlException(this.facebookService.getAuthorizationUrl(FacebookClient.EMPTY_TOKEN));
	}

	public final OAuthService getOauthService() {
		return this.facebookService;
	}
}
