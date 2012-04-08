package com.nadeem.app.util;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

public class FacebookClient {

	private static final String FACEBOOK_GRAPH_API_URL = "https://graph.facebook.com";
	public static final Token EMPTY_TOKEN 				= null;
	
	private final OAuthService service;
	private final String accessToken;

	public FacebookClient(OAuthService newService, String accessToken) {
		this.service = newService;
		this.accessToken = accessToken;
	}

	public <T> T fetch(Verb verb, String what, Class<T> classOfT) {

		Verifier verifier = new Verifier(accessToken);
		Token accessToken = service.getAccessToken(EMPTY_TOKEN, verifier);

		// getting user profile
		OAuthRequest oauthRequest = new OAuthRequest(verb, FACEBOOK_GRAPH_API_URL + what);
		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();
		return new Gson().fromJson(oauthResponse.getBody(), classOfT);		
	}
}
