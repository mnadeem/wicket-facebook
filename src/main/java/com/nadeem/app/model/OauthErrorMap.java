package com.nadeem.app.model;

import java.io.Serializable;
import java.util.Map;

public class OauthErrorMap implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> error = null;


	public void setError(Map<String, String> error) {
		this.error = error;
	}
	
	public OAuthError buildOAuthError() {
		if (error != null) {
			OAuthError oAuthError = new OAuthError();
			oAuthError.setCode(error.get("code"));
			oAuthError.setMessage(error.get("message"));
			oAuthError.setType(error.get("type"));
			
			return oAuthError;
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format("OauthErrorMap[%s]", error);
	}
}
