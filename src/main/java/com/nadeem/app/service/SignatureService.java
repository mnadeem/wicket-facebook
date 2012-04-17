package com.nadeem.app.service;

public interface SignatureService {
	String getSignature(final String baseString, final String apiSecret);
	String getSignatureMethod();
}
