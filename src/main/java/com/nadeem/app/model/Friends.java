package com.nadeem.app.model;

import java.util.List;

public class Friends {

	private List<Friend> data;

	public final List<Friend> all() {
		return data;
	}

	public final void setData(final List<Friend> data) {
		this.data = data;
	}

	@Override
	public final String toString() {
		return String.format("Freinds[%s]", data);
	}
}
