package com.nadeem.app.facebook;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FacebookData {

	private final JsonObject jsonObject;
	
	public FacebookData(String jsonData) {
		this.jsonObject = new JsonParser().parse(jsonData).getAsJsonObject();
	}

	public JsonObject getError() {
		return getAsJsonObject("error");
	}

	public boolean isAlgorithmHMAC_SHA256() {
		return getAlgorithm().equals("HMAC-SHA256");
	}

	public String getAlgorithm() {
		return getAsString("algorithm");
	}
	
	public String getUserId() {
		return getAsString("user_id");
	}
	
	public boolean hasUserId() {
		return has("user_id");
	}
	
	public boolean isUserAuthorizedApp() {
		return hasAuthToken();
	}

	public boolean hasAuthToken() {
		return has("oauth_token");
	}
	
	public String getAuthToken() {
		return getAsString("oauth_token");
	}

	public JsonObject getAsJsonObject(String memberName) {
		return jsonObject.get(memberName).getAsJsonObject();
	}

	public String getAsString(String memberName) {
		return jsonObject.get(memberName).getAsString();
	}
	
	public boolean has(String memberName) {
		return jsonObject.has(memberName);
	}
}
