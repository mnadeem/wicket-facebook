package com.nadeem.app.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FacebookServiceConfigTest.class,
		FacebookServiceProviderTest.class, FacebookServiceTest.class,
		HMACSha256SignatureServiceTest.class })
public class AllServiceTests {

}
