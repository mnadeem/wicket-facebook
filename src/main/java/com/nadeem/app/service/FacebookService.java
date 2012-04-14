package com.nadeem.app.service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FacebookService {

	public static final Token EMPTY_TOKEN 	= null;

	private FacebookServiceConfig config;
	private OAuthService oAuthService;

	public FacebookService(final FacebookServiceConfig newConfig) {
		this.config 		= newConfig;
		this.oAuthService 	= buildOauAuthService();
	}

	private OAuthService buildOauAuthService() {
		return new ServiceBuilder()
		.provider(this.config.getApiClass())
		.apiKey(this.config.getApiKey())
		.apiSecret(this.config.getApiSecret())
		.callback(this.config.getCallback())
		.build();
	}

	public Token extractAccessToken(final String signedRequest) throws Exception {
		//it is important to enable url-safe mode for Base64 encoder 
		Base64 base64 = new Base64(true);

		//split request into signature and Json data
		String[] splittedSignedRequest = signedRequest.split("\\.", 2);

		String encodedSignature = new String(base64.decode(splittedSignedRequest[0].getBytes("UTF-8")));
		String encodedJsonData 	= new String(base64.decode(splittedSignedRequest[1].getBytes("UTF-8")));

		//parse data and convert to json object
		JsonObject decodedJsonObject = new JsonParser().parse(encodedJsonData).getAsJsonObject();

		//check signature algorithm
		if(!decodedJsonObject.get("algorithm").getAsString().equals("HMAC-SHA256")) {
			//unknown algorithm is used
			return null;
		}

		if(!dataSignedCorrectly(encodedJsonData, encodedSignature)) {
			//signature is not correct, possibly the data was tampered with
			return null;
		}

		//check if user authorized the app
		if(!decodedJsonObject.has("user_id") || !decodedJsonObject.has("oauth_token")) {
			//this is guest
			return null;

		} else {
			//this is authorized user
			String oauthVerifier = decodedJsonObject.get("oauth_token").getAsString();
			return getAccessToken(oauthVerifier);

		}

	}

	private boolean dataSignedCorrectly(String encodedJsonData, String signature) throws Exception {
		return hmacSHA256(encodedJsonData, this.config.getApiSecret()).equals(signature);
	}

	private String hmacSHA256(String data, String key) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(secretKey);
		byte[] hmacData = mac.doFinal(data.getBytes("UTF-8"));
		return new String(hmacData);
	}

	public Token getAccessToken(final String oauthVerifier) {
		return oAuthService.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
	}

	public void signRequest(Token accessToken, OAuthRequest request) {
		this.oAuthService.signRequest(accessToken, request);
	}

	public String getAuthorizationUrl() {
		return this.oAuthService.getAuthorizationUrl(EMPTY_TOKEN);
	}
}
