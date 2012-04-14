package com.nadeem.app.facebook;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

public class FacebookRequest extends OAuthRequest {

	private static final String FACEBOOK_GRAPH_API_URL = "https://graph.facebook.com";

	public FacebookRequest(Verb how, String what) {
		super(how,  FACEBOOK_GRAPH_API_URL + what);
	}
}
