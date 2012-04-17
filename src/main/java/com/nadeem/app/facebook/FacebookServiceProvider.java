package com.nadeem.app.facebook;

import org.scribe.builder.ServiceBuilder;
import org.scribe.oauth.OAuthService;

public class FacebookServiceProvider {

	private FacebookServiceConfig config;

	public FacebookServiceProvider(final FacebookServiceConfig newConfig) {
		this.config = newConfig;
	}

	public final OAuthService getService() {

		return new ServiceBuilder()
							.provider(this.config.getApiClass())
							.apiKey(this.config.getApiKey())
						    .apiSecret(this.config.getApiSecret())
						    .callback(this.config.getCallback())
						    .build();
	}
}
