package com.viki3d.javacodeconventions.logic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.viki3d.javacodeconventions.Main;
import com.viki3d.javacodeconventions.front.api.NumbersApi;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Testing Odds.
 *
 * @author viki3d
 */
@SpringBootTest(classes = { Main.class, NumbersService.class })
@DisplayName("NumbersService Odds Tests")
@TestMethodOrder(OrderAnnotation.class)
public class NumbersServiceOddsTests {

  private static final Logger logger = LoggerFactory
      .getLogger(NumbersServiceOddsTests.class.getName());

  @Autowired
  private NumbersService numbersService;

  @BeforeAll
  public static void initAll() {
    logger.trace("BeforeAll");
    Assumptions.assumeTrue(NumbersApi.Limits.values().length == 2);
  }

  @Test
  @Order(1)
  @DisplayName("Test odds: test NumbersApi.Limits")
  void oddsBaseTest() {
    assertTrue(NumbersApi.Limits.MIN.getValue() < NumbersApi.Limits.MAX.getValue());
    assertTrue(NumbersApi.Limits.MIN.getValue() == 0);
    assertTrue(NumbersApi.Limits.MAX.getValue() > 0);
  }
  
  @Test
  @Order(2)
  @DisplayName("Test odds: number = 5")
  void oddsInputFixedFive() {
    int number = 5;
    List<Integer> squares = numbersService.odds(number);

    int expectedArrayLength = (number / 2) + (number % 2);
    assertEquals(squares.size(), expectedArrayLength);
    assertEquals(squares.get(squares.size() - 1).intValue(), number);
  }
  
  @Test
  @Order(3)
  @DisplayName("Test odds: number = 0")
  void oddsInputFixedZero() {
    int number = 0;
    List<Integer> squares = numbersService.odds(number);

    int expectedArrayLength = (number / 2) + (number % 2);
    assertEquals(squares.size(), expectedArrayLength);
  }
  
  
  @Test
  @Order(4)
  @DisplayName("Test odds: number = random")
  void oddsInputRandom() {
    int number = NumbersApi.Limits.MIN.getValue() 
        + new Random().nextInt(NumbersApi.Limits.MAX.getValue() - 1);
    List<Integer> squares = numbersService.odds(number);

    boolean allInRange = squares.stream().allMatch(n -> 
        n >= 0 && n <= NumbersApi.Limits.MAX.getValue());

    int expectedArrayLength = (number / 2) + (number % 2);
    assertEquals(squares.size(), expectedArrayLength);
    assertEquals(allInRange, true);
  }
  
  @Test
  @Order(5)
  @DisplayName("Test odds: number = invalid negative")
  void oddsInputInvalidNegative() {
    int number = -1;

    assertThrows(IllegalArgumentException.class, () -> { 
      numbersService.odds(number); });
  }

  @Test
  @Order(6)
  @DisplayName("Test odds: number = invalid max exceed")
  void oddsInputInvalidTooMuch() {
    int number = NumbersApi.Limits.MAX.getValue() + 1;

    assertThrows(IllegalArgumentException.class, () -> {
      numbersService.odds(number);  });
  }
  
  @AfterAll
  public static void tearDownAll() {
    logger.trace("AfterAll");
  }
  
  
}
