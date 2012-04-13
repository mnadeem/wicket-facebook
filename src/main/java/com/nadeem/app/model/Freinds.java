package com.nadeem.app.model;

import java.util.List;

public class Freinds {

	private List<Friend> data;

	public List<Friend> getData() {
		return data;
	}

	public void setData(List<Friend> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return String.format("Freinds[%s]", data);
	}
}
