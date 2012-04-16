package com.nadeem.app.facebook;

import com.nadeem.app.exception.ErrorType;
import com.nadeem.app.exception.FacebookException;
import com.nadeem.app.service.FacebookService;

public class FacebookTokenExtractor {
	
	private final FacebookService service;
	
	public FacebookTokenExtractor(final FacebookService newService) {
		this.service = newService;		
	}
	
	public FacebookToken extract(final FacebookParameterProvider paramProvider) throws Exception {
		final String oauthVerifier 	= paramProvider.getParameter(FacebookParameterProvider.AUTH_CODE);
		final String signedRequest 	= paramProvider.getParameter(FacebookParameterProvider.SIGNED_REQUEST);
		
		FacebookToken accessToken;

		if (signedRequest != null) {
			accessToken 	= service.extractAccessToken(signedRequest);
		} else if (oauthVerifier != null) {
			accessToken 	= service.getAccessToken(oauthVerifier);
		} else {
			throw new FacebookException(ErrorType.AUTHERROR); 
		}		
		return accessToken;
	}
}
