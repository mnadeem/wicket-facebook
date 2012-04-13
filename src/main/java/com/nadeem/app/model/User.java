package com.nadeem.app.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName("id")
	private String userId;
	@SerializedName("first_name")
	private String firstName;
	@SerializedName("last_name")
	private String lastName;
	private String oauthVerifier;

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getOauthVerifier() {
		return oauthVerifier;
	}
	public void setOauthVerifier(String oauthVerifier) {
		this.oauthVerifier = oauthVerifier;
	}

	@Override
	public String toString() {
		return String.format("User[%s , %s, %s , %s]", userId, firstName, lastName, oauthVerifier);
	}

}
