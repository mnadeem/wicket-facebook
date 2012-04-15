package com.nadeem.app.service;

import java.io.UnsupportedEncodingException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.nadeem.app.facebook.FacebookData;
import com.nadeem.app.facebook.FacebookToken;

public class FacebookService {

	private static final String HMAC_SHA256 = "HmacSHA256";
	private static final String UTF8 		= "UTF-8";

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

	public FacebookToken extractAccessToken(final String signedRequest) throws Exception {

		String[] parsedSignedRequest= parse(signedRequest);

		String decodedSignature 	= decode(parsedSignedRequest[0]);
		String decodedJsonData 		= decode(parsedSignedRequest[1]);
		FacebookData facebookData 	= new FacebookData(decodedJsonData);

		if (!facebookData.isAlgorithmHMAC_SHA256()) {
			//TODO: unknown algorithm is used
			return null;
		}

		if (!dataSignedCorrectly(parsedSignedRequest[1], decodedSignature)) {
			//signature is not correct, possibly the data was tampered with
			return null;
		}

		if (!facebookData.userHasAuthorizedTheApp()) {
			//TODO: this is guest
			return null;

		} else {
			//this is authorized user
			return new FacebookToken(facebookData);
		}
	}

	private String[] parse(final String signedRequest) {
		return signedRequest.split("\\.", 2);
	}

	private String decode(String data) throws UnsupportedEncodingException {
		return new String(new Base64(true).decode(data.getBytes(UTF8)));
	}

	private boolean dataSignedCorrectly(String decodedJsonData, String decodedSignature) throws Exception {
		return hmacSHA256(decodedJsonData, this.config.getApiSecret()).equals(decodedSignature);
	}

	private String hmacSHA256(String data, String appSeceretKey) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(appSeceretKey.getBytes(UTF8), HMAC_SHA256);
		Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKey);
		byte[] hmacData = mac.doFinal(data.getBytes(UTF8));
		return new String(hmacData);
	}

	public FacebookToken getAccessToken(final String oauthVerifier) {
		Token token = oAuthService.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
		return new FacebookToken(token, null, System.currentTimeMillis());
	}

	public void signRequest(Token accessToken, OAuthRequest request) {
		this.oAuthService.signRequest(accessToken, request);
	}

	public String getAuthorizationUrl() {
		return this.oAuthService.getAuthorizationUrl(EMPTY_TOKEN);
	}
}
