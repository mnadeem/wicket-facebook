package com.nadeem.app.facebook;

import org.scribe.model.Response;
import org.scribe.model.Verb;

import com.google.gson.Gson;
import com.nadeem.app.exception.FacebookException;
import com.nadeem.app.model.FacebookError;
import com.nadeem.app.model.Friends;
import com.nadeem.app.model.User;
import com.nadeem.app.service.FacebookService;

public class FacebookClient {

	private final FacebookService service;
	private final FacebookToken accessToken;

	public FacebookClient(final FacebookService newService, final String oauthVerifier) {
		this.service 	= newService;
		accessToken 	= service.getAccessToken(oauthVerifier);
	}

	public final User getLoggedInUser() {
		return fetch(Verb.GET, FacebookObject.ME, User.class);
	}

	public final Friends getFriends() {
		return fetch(Verb.GET, FacebookObject.FRIENDS, Friends.class);
	}

	public final <T> T fetch(final Verb how, final FacebookObject what, final Class<T> classOfT) {
		return fetch(how, what.getPath(), classOfT);
	}

	public final <T> T fetch(final Verb how, final String what, final Class<T> classOfT) {

		FacebookRequest oauthRequest 	= new FacebookRequest(how, what);
		service.signRequest(accessToken, oauthRequest);

		Response oauthResponse = oauthRequest.send();

		if (oauthResponse.isSuccessful()) {
			return new Gson().fromJson(oauthResponse.getBody(), classOfT);
		}
		FacebookData root = new FacebookData(oauthResponse.getBody());
		throw new FacebookException(new Gson().fromJson(root.error(), FacebookError.class));
	}
}
