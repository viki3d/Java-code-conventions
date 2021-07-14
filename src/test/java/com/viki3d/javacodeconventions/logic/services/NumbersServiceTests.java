package com.viki3d.javacodeconventions.logic.services;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/* Test Suite for testing all operations [squares, odds, sum] at once. */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Platform Suite Demo")
//@SelectPackages("com.viki3d.javacodeconventions.logic.services")
@SelectClasses({ NumbersServiceSquaresTests.class, NumbersServiceOddsTests.class, 
	NumbersServiceSumTests.class })
public class NumbersServiceTests {

	
}
