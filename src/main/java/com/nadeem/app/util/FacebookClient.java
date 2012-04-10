package com.nadeem.app.util;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;
import com.nadeem.app.model.User;

public class FacebookClient {

	private static final String FACEBOOK_GRAPH_API_URL = "https://graph.facebook.com";
	public static final Token EMPTY_TOKEN 				= null;
	
	private final OAuthService service;
	private final String oauthVerifier;

	public FacebookClient(OAuthService newService, String accessToken) {
		this.service = newService;
		this.oauthVerifier = accessToken;
	}
	
	public User getFacebookUser() {
		Token accessToken 			= service.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
		OAuthRequest oauthRequest 	= new OAuthRequest(Verb.GET, FACEBOOK_GRAPH_API_URL + "/me");
		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();
		return new Gson().fromJson(oauthResponse.getBody(), User.class);		
	}

	public <T> T fetch(Verb how, String what, Class<T> classOfT) {

		Token accessToken 			= service.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
		OAuthRequest oauthRequest 	= new OAuthRequest(how, FACEBOOK_GRAPH_API_URL + what);
		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();
		return new Gson().fromJson(oauthResponse.getBody(), classOfT);		
	}
}
