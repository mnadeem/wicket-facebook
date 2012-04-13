package com.nadeem.app.service;

import org.scribe.builder.api.Api;

public class OAuthServiceConfig {
	private String apiKey;
	private String apiSecret;
	private String callback;
	private Class<? extends Api> apiClass;

	public OAuthServiceConfig() {
	}

	public OAuthServiceConfig(final String apiKey, final String apiSecret, final String callback,
            final Class<? extends Api> apiClass) {
	    super();
	    this.apiKey = apiKey;
	    this.apiSecret = apiSecret;
	    this.callback = callback;
	    this.apiClass = apiClass;
    }

    public final String getApiKey() {
    	return apiKey;
    }

    public final void setApiKey(final String apiKey) {
    	this.apiKey = apiKey;
    }

    public final String getApiSecret() {
    	return apiSecret;
    }

    public final void setApiSecret(final String apiSecret) {
    	this.apiSecret = apiSecret;
    }

    public final String getCallback() {
    	return callback;
    }

    public final void setCallback(final String callback) {
    	this.callback = callback;
    }


    public final Class<? extends Api> getApiClass() {
    	return apiClass;
    }

    public final void setApiClass(final Class<? extends Api> apiClass) {
    	this.apiClass = apiClass;
    }

    @Override
	public final String toString() {
	    return "OAuthServiceConfig [apiKey=" + apiKey + ", apiSecret="
	            + apiSecret + ", callback=" + callback + ", apiClass="
	            + apiClass + "]";
    }

}
