package com.nadeem.app.exception;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ErrorHelperTest.class, ErrorTypeTest.class,
		FacebookExceptionTest.class })
public class AllExceptionTests {

}
