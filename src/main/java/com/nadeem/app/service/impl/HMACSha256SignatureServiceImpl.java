package com.nadeem.app.service.impl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.scribe.exceptions.OAuthSignatureException;

import com.nadeem.app.service.SignatureService;

public class HMACSha256SignatureServiceImpl implements SignatureService {

	private static final String CHAR_ENCODING 	= "UTF-8";
	private static final String METHOD 			= "HmacSHA256";

	public final String getSignature(final String baseString, final String apiSecret) {
		try {

			return doSign(baseString, apiSecret);
		}  catch (Exception e) {
			throw new OAuthSignatureException(baseString, e);
		}
	}

	private String doSign(final String toSign, final String keyString) throws Exception {
		SecretKeySpec key 	= new SecretKeySpec((keyString).getBytes(CHAR_ENCODING), METHOD);
		Mac mac 			= getMac(key);
		byte[] bytes 		= mac.doFinal(toSign.getBytes(CHAR_ENCODING));
		return new String(bytes);
	}

	private Mac getMac(final SecretKeySpec key) throws Exception {
		Mac mac = Mac.getInstance(METHOD);
		mac.init(key);
		return mac;
	}

	public final String getSignatureMethod() {
		return METHOD;
	}
}
