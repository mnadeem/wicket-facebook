package com.nadeem.app.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FacebookErrorTest.class, FriendsTest.class, FriendTest.class,
		UserTest.class })
public class AllModelTests {

}
