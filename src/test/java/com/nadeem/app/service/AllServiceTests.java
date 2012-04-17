package com.nadeem.app.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nadeem.app.facebook.FacebookServiceConfigTest;

@RunWith(Suite.class)
@SuiteClasses({ FacebookServiceConfigTest.class,
		HMACSha256SignatureServiceTest.class })
public class AllServiceTests {

}
