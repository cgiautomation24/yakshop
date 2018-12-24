package com.test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestServerStatusJunit.class, TestStockJunit.class, TestOrderJunit.class, TestHerdJunit.class })
public class AllTests {

}
