package com.nadeem.app.facebook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FacebookDataTest.class, FacebookObjectTest.class,
		FacebookTokenExtractorTest.class })
public class AllFacebookTests {

}
