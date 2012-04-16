package com.nadeem.app;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.nadeem.app.exception.AllExceptionTests;
import com.nadeem.app.facebook.AllFacebookTests;
import com.nadeem.app.model.AllModelTests;
import com.nadeem.app.service.AllServiceTests;
import com.nadeem.app.web.AllWebTests;

@RunWith(Suite.class)
@SuiteClasses({ AllExceptionTests.class,
	AllFacebookTests.class,
	AllModelTests.class,
	AllServiceTests.class,
	AllWebTests.class })
public class AllTests {

}
