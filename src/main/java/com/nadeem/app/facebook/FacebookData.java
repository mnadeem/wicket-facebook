package com.nadeem.app.facebook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FacebookData {

	private final JsonObject jsonObject;

	public FacebookData(final String jsonData) {
		this.jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
	}

	public final Long issuedAt() {
		return getAsLong("issued_at");
	}

	public final Long expires() {
		return getAsLong("expires");
	}

	public final JsonObject error() {
		return getAsJsonObject("error");
	}

	public final boolean isAlgorithmHMAC_SHA256() {
		return algorithm().equals("HMAC-SHA256");
	}

	public final String algorithm() {
		return getAsString("algorithm");
	}

	public final String userId() {
		return getAsString("user_id");
	}

	public final boolean hasUserId() {
		return has("user_id");
	}

	public final boolean userHasAuthorizedTheApp() {
		return hasAuthToken();
	}

	public final boolean hasAuthToken() {
		return has("oauth_token");
	}

	public final String authToken() {
		return getAsString("oauth_token");
	}

	public final JsonObject getAsJsonObject(final String memberName) {
		return jsonObject.get(memberName).getAsJsonObject();
	}

	public final String getAsString(final String memberName) {
		return jsonObject.get(memberName).getAsString();
	}

	public final Long getAsLong(final String memberName) {
		return jsonObject.get(memberName).getAsLong();
	}

	public final boolean has(final String memberName) {
		return jsonObject.has(memberName);
	}
}
