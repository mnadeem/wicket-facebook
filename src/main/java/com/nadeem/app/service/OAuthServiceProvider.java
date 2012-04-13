package com.nadeem.app.service;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class OAuthServiceProvider {

	private OAuthServiceConfig config;

	public OAuthServiceProvider(final OAuthServiceConfig config) {
		this.config = config;
	}

	public final OAuthService getService() {

		return new ServiceBuilder().provider(this.config.getApiClass())
							.apiKey(this.config.getApiKey())
						    .apiSecret(this.config.getApiSecret())
						    .callback(this.config.getCallback())
						    .build();
	}

}
