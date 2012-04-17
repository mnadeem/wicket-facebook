package com.nadeem.app.service;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;

import com.nadeem.app.facebook.FacebookToken;

public interface FacebookService {
	
	FacebookToken extractAccessToken(final String signedRequest) throws Exception;
	FacebookToken getAccessToken(final String oauthVerifier);
	void signRequest(final Token accessToken, final OAuthRequest request);
	String getAuthorizationUrl();
}
