package com.nadeem.app.facebook;

import org.scribe.model.Token;

public class FacebookToken extends Token {

	private static final long serialVersionUID = 1L;

	private Long expires;
	private Long issuedAt;

	public FacebookToken(final FacebookData facebookData) {
		super(facebookData.authToken(), null, facebookData.toString());
		this.expires 	= facebookData.expires();
		this.issuedAt 	= facebookData.issuedAt();
	}

	public FacebookToken(final Token token) {
		super(token.getToken(), token.getSecret(), token.getRawResponse());
	}

	public FacebookToken(final Token token, final Long expires, final Long issuedAt) {
		this(token);
		this.expires = expires;
		this.issuedAt = issuedAt;
	}

	public final Long getExpires() {
		return expires;
	}

	public final void setExpires(final Long expires) {
		this.expires = expires;
	}

	public final Long getIssuedAt() {
		return issuedAt;
	}

	public final void setIssuedAt(final Long issuedAt) {
		this.issuedAt = issuedAt;
	}
	
	 @Override
	  public String toString() {
	    return String.format("FacebookToken[%s , %s]", getToken(), getRawResponse());
	  }
}
