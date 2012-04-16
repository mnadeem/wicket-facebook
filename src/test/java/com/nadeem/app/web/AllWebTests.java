package com.nadeem.app.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FacebookPageAuthorizationStrategyTest.class,
		FacebookRequestcycleTest.class, FacebookSessionTest.class,
		WicketFacebookParameterProviderTest.class })
public class AllWebTests {

}
