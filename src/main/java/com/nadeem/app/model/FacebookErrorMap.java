package com.nadeem.app.model;

import java.io.Serializable;
import java.util.Map;
/**
 * Facebook returns error in the following format
 * {
	"error":{
				"message":"Error validating access token: User 24324234234 has not authorized application 5453453453.",
				"type":"OAuthException",
				"code":190
			}
	}
 *  
 *
 */
public class FacebookErrorMap implements Serializable {

	private static final long serialVersionUID = 1L;
	private Map<String, String> error = null;


	public final void setError(final Map<String, String> error) {
		this.error = error;
	}

	public final FacebookError buildOAuthError() {
		if (this.error != null) {
			FacebookError oAuthError = new FacebookError();
			oAuthError.setCode(this.error.get("code"));
			oAuthError.setMessage(this.error.get("message"));
			oAuthError.setType(this.error.get("type"));

			return oAuthError;
		}
		return new FacebookError();
	}

	@Override
	public final String toString() {
		return String.format("FacebookErrorMap[%s]", this.error);
	}
}
