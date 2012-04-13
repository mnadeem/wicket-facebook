package com.nadeem.app.web.page;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import com.nadeem.app.model.Friend;

public class WelcomePage extends BasePage {

	private static final long serialVersionUID = 1L;

    public WelcomePage(final PageParameters parameters) {
    	super(parameters);
    	
    	getSession().bind();
    	add(new Label("facebookUser", getLoggedInUser().getFirstName()));
    	
    	add(newFriendsListView());

    }

	private ListView<Friend> newFriendsListView() {
		return new ListView<Friend>("friends", getFacebookClient().getFriends().all()) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Friend> item) {
				item.add(new Label("id", item.getModelObject().getId()));
				item.add(new Label("name", item.getModelObject().getName()));	
			}					
		};
	}
}
