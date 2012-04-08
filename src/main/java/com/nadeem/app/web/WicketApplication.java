package com.nadeem.app.web;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.scribe.builder.api.FacebookApi;
import org.scribe.oauth.OAuthService;

import com.nadeem.app.service.OAuthServiceConfig;
import com.nadeem.app.service.OAuthServiceProvider;
import com.nadeem.app.web.page.LoginPage;

public class WicketApplication extends WebApplication {
	
	private OAuthService oauthService;

	public WicketApplication() 	{

	}

	@Override
	public Session newSession(Request request, Response response) {
		return new FacebookSession(request);
	}
	
	@Override
	protected void init() {
		super.init();
		OAuthServiceProvider provider 	= new OAuthServiceProvider(new OAuthServiceConfig(getAppKey(), getSecret(), getCallback(), FacebookApi.class));
		oauthService 						= provider.getService();
		
		mountBookmarkablePage("/facebook-callback/", LoginPage.class);
	}
	
	public OAuthService getOAuthService() {
		return oauthService;
	}

	public Class<? extends WebPage> getHomePage() {
		return LoginPage.class;
	}
	
	public String getAppKey() {
		return getInitParameter("appKey");
	}
	
	public String getSecret() {
		return getInitParameter("secret");
	}
	
	public String getCallback() {
		return getInitParameter("callback");
	}
}
