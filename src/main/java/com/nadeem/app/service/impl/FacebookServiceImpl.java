package com.nadeem.app.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import com.nadeem.app.exception.ErrorType;
import com.nadeem.app.exception.FacebookException;
import com.nadeem.app.facebook.FacebookData;
import com.nadeem.app.facebook.FacebookServiceConfig;
import com.nadeem.app.facebook.FacebookToken;
import com.nadeem.app.service.FacebookService;

public class FacebookServiceImpl implements FacebookService {

	public static final Token EMPTY_TOKEN 	= null;
	private static final String UTF8 		= "UTF-8";

	private FacebookServiceConfig config;
	private OAuthService oAuthService;
	private HMACSha256SignatureServiceImpl signatureService;

	public FacebookServiceImpl(final FacebookServiceConfig newConfig) {
		this.config 			= newConfig;
		this.oAuthService 		= buildOauAuthService();
		this.signatureService 	= new HMACSha256SignatureServiceImpl();
	}

	private OAuthService buildOauAuthService() {
		return new ServiceBuilder()
						.provider(this.config.getApiClass())
						.apiKey(this.config.getApiKey())
						.apiSecret(this.config.getApiSecret())
						.callback(this.config.getCallback())
						.build();
	}

	public final FacebookToken extractAccessToken(final String signedRequest) throws Exception {

		String[] parsedSignedRequest= parse(signedRequest);

		String rawJsonData			= parsedSignedRequest[1];
		String decodedSignature 	= decode(parsedSignedRequest[0]);
		String decodedJsonData 		= decode(rawJsonData);
		FacebookData facebookData 	= new FacebookData(decodedJsonData);

		if (!facebookData.isAlgorithmHMAC_SHA256()) {
			throw new IllegalArgumentException("Unknown Algorithm " + facebookData.algorithm());
		}

		if (!isSignatureValid(rawJsonData, decodedSignature)) {
			//signature is not correct, possibly the data was tampered with
			throw new FacebookException(ErrorType.AUTHERROR);
		}

		if (!facebookData.userHasAuthorizedTheApp()) {
			//this is guest
			throw new FacebookException(ErrorType.AUTHERROR);
		} else {
			//this is authorized user
			return new FacebookToken(facebookData);
		}
	}

	private String[] parse(final String signedRequest) {
		return signedRequest.split("\\.", 2);
	}

	private String decode(final String data) throws UnsupportedEncodingException {
		return new String(new Base64(true).decode(data.getBytes(UTF8)));
	}

	private boolean isSignatureValid(final String encodedJsonData, final String decodedSignature) throws Exception {
		String appSignature = signatureService.getSignature(encodedJsonData, this.config.getApiSecret());
		return appSignature.equals(decodedSignature);
	}

	public final FacebookToken getAccessToken(final String oauthVerifier) {
		Token token = oAuthService.getAccessToken(EMPTY_TOKEN,  new Verifier(oauthVerifier));
		return new FacebookToken(token, null, System.currentTimeMillis());
	}

	public final void signRequest(final Token accessToken, final OAuthRequest request) {
		this.oAuthService.signRequest(accessToken, request);
	}

	public final String getAuthorizationUrl() {
		return this.oAuthService.getAuthorizationUrl(EMPTY_TOKEN);
	}
}
