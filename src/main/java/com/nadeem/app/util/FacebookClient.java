package com.nadeem.app.util;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.nadeem.app.exception.OAuthException;
import com.nadeem.app.model.OauthErrorMap;
import com.nadeem.app.model.User;

public class FacebookClient {

	private static final String FACEBOOK_GRAPH_API_URL = "https://graph.facebook.com";
	public static final Token EMPTY_TOKEN 				= null;
	
	private final OAuthService service;
	private final Token accessToken;

	public FacebookClient(OAuthService newService, String oauthVerifier) {
		this.service 	= newService;
		accessToken 	= service.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
	}

	public User getFacebookUser() {
		return fetch(Verb.GET,"/me", User.class);		
	}

	public <T> T fetch(Verb how, String what, Class<T> classOfT) {

		OAuthRequest oauthRequest 	= new OAuthRequest(how, FACEBOOK_GRAPH_API_URL + what);
		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();

		if (oauthResponse.isSuccessful()) {
			return new Gson().fromJson(oauthResponse.getBody(), classOfT);
		}
		throw new OAuthException(new Gson().fromJson(oauthResponse.getBody(), OauthErrorMap.class).buildOAuthError());		
	}	
}
