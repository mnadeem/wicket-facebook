package com.nadeem.app.service;

import java.io.Serializable;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.FacebookApi;

public class FacebookServiceConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private String apiKey;
	private String apiSecret;
	private String callback;
	private String scope;

	public FacebookServiceConfig() {

	}

	public FacebookServiceConfig(final String apiKey, final String apiSecret, final String callback) {
	    super();
	    this.apiKey = apiKey;
	    this.apiSecret = apiSecret;
	    this.callback = callback;
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
    	return FacebookApi.class;
    }

    public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public final String toString() {
    	return String.format("FacebookServiceConfig[apiKey=%s , apiSecret=%s, callback=%s]", this.apiKey, this.apiSecret, this.callback);
    }

}
