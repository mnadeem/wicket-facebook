package com.nadeem.app.facebook;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nadeem.app.service.FacebookServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ FacebookDataTest.class, FacebookObjectTest.class,
		FacebookServiceProviderTest.class, 
		FacebookServiceTest.class,
		FacebookTokenExtractorTest.class })
public class AllFacebookTests {

}
