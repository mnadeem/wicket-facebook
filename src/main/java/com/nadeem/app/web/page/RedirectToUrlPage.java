package com.nadeem.app.web.page;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;

public class RedirectToUrlPage extends WebPage implements IHeaderContributor {

	private final String redirectUrl;

	public RedirectToUrlPage(final String redirectUrl) {
		this.redirectUrl = redirectUrl;
		getSession().invalidateNow();
	}

	public void renderHead(IHeaderResponse response) {
		response.renderJavascript("top.location = '" + this.redirectUrl + "';", "redirectID");

	}

	@Override
	public boolean isErrorPage() {
		return true;
	}
}
