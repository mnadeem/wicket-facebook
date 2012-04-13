package com.nadeem.app.model;

import java.io.Serializable;
import java.util.Map;

public class OauthErrorMap implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> error = null;


	public final void setError(final Map<String, String> error) {
		this.error = error;
	}

	public final OAuthError buildOAuthError() {
		if (this.error != null) {
			OAuthError oAuthError = new OAuthError();
			oAuthError.setCode(this.error.get("code"));
			oAuthError.setMessage(this.error.get("message"));
			oAuthError.setType(this.error.get("type"));

			return oAuthError;
		}
		return null;
	}

	@Override
	public final String toString() {
		return String.format("OauthErrorMap[%s]", this.error);
	}
}
