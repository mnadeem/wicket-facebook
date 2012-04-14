package com.nadeem.app.model;

import java.util.List;
/**
 * Facebook returns data in the following format
 * {
	"data":
			[
				{"name":"Brandon Rich","id":"32501837"},
				{"name":"AbdulBaqi","id":"522057086"}
			],
	"paging":
			{"next":"https:\/\/graph.facebook.com\/me\/friends?access_token=someBigToken&limit=5000&offset=5000&__after_id=someID"}
}
 *
 */
		
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
		return String.format("Freinds[data=%s]", data);
	}
}
