package com.viki3d.javacodeconventions.logic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.viki3d.javacodeconventions.Main;
import com.viki3d.javacodeconventions.front.api.NumbersApi;

@SpringBootTest(classes = { Main.class, NumbersService.class })
@DisplayName("NumbersService Sum Tests")
@TestMethodOrder(OrderAnnotation.class)
public class NumbersServiceSumTests {

	private static final Logger logger = LoggerFactory
			.getLogger(NumbersServiceSumTests.class.getName());
	
	@Autowired
	private NumbersService numbersService;

	@BeforeAll
	public static void initAll() {
		logger.trace("BeforeAll");
		Assumptions.assumeTrue(NumbersApi.Limits.values().length == 2);
	}

	@Test
	@Order(1)
	@DisplayName("Test squares: test NumbersApi.Limits")
	void oddsBaseTest() {
		assertTrue(NumbersApi.Limits.MIN.getValue() < NumbersApi.Limits.MAX.getValue());
		assertTrue(NumbersApi.Limits.MIN.getValue() == 0);
		assertTrue(NumbersApi.Limits.MAX.getValue() > 0);
	}
	
	@Test
	@Order(2)
	@DisplayName("Test sum: number = 5")
	void squaresInputFixedFive() {
		int number = 5;
		int sum = numbersService.sum(number);
		logger.debug("calculated sum = " + sum);
		
		assertEquals(sum, 15);
	}
	
	@Test
	@Order(3)
	@DisplayName("Test sum: number = 0")
	void squaresInputFixedZero() {
		int number = 0;
		int sum = numbersService.sum(number);
		logger.debug("calculated sum = " + sum);
		
		assertEquals(sum, 0);
	}
	
	
	@Test
	@Order(4)
	@DisplayName("Test sum: number = random")
	void squaresInputRandom() {
		int number = NumbersApi.Limits.MIN.getValue() 
				+ new Random().nextInt(NumbersApi.Limits.MAX.getValue() - 1);
		int sum = numbersService.sum(number);
		logger.debug("Random: " + number + ", calculated sum = " + sum);
		
		assertTrue(number > 1 ? (number < sum) : (number == sum));
	}
	
	@Test
	@Order(5)
	@DisplayName("Test sum: number = invalid negative")
	void squaresInputInvalidNegative() {
		int number = -1;

		assertThrows(IllegalArgumentException.class, () -> { numbersService.sum(number);  } );
	}
	
	@Test
	@Order(6)
	@DisplayName("Test sum: number = invalid max exceed")
	void squaresInputInvalidTooMuch() {
		int number = NumbersApi.Limits.MAX.getValue() + 1;

		assertThrows(IllegalArgumentException.class, () -> { numbersService.sum(number);  } );
	}
	
	@AfterAll
	public static void tearDownAll() {
		logger.trace("AfterAll");
	}
	
	
}
