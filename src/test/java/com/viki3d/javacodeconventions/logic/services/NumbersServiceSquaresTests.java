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
 * Testing Squares.
 *
 * @author viki3d
 */
@SpringBootTest(classes = { Main.class, NumbersService.class })
@DisplayName("NumbersService Squares Tests")
@TestMethodOrder(OrderAnnotation.class)
public class NumbersServiceSquaresTests {

  private static final Logger logger = LoggerFactory
      .getLogger(NumbersServiceSquaresTests.class.getName());
  
  @Autowired
  private NumbersService numbersService;

  /** Set up with expected preconditions. */
  @BeforeAll
  public static void initAll() {
    logger.trace("BeforeAll");
    // All further tests will be executed only if this condition is met:
    Assumptions.assumeTrue(NumbersApi.Limits.values().length == 2);
  }

  @Test
  @Order(1)
  @DisplayName("Test squares: test NumbersApi.Limits")
  void squaresBaseTest() {
    assertTrue(NumbersApi.Limits.MIN.getValue() < NumbersApi.Limits.MAX.getValue());
    assertTrue(NumbersApi.Limits.MIN.getValue() == 0);
    assertTrue(NumbersApi.Limits.MAX.getValue() > 0);
  }
  
  @Test
  @Order(2)
  //@Disabled
  @DisplayName("Test squares: number = 5")
  void squaresInputFixedFive() {
    int number = 5;
    List<Integer> squares = numbersService.squares(number);

    assertEquals(squares.size(), number + 1);
    assertEquals(squares.get(squares.size() - 1).intValue(), number * number);
  }

  @Test
  @Order(3)
  @DisplayName("Test squares: number = 0")
  void squaresInputFixedZero() {
    int number = 0;
    List<Integer> squares = numbersService.squares(number);

    assertEquals(squares.size(), number + 1);
    assertEquals(squares.get(squares.size() - 1).intValue(), number * number);
  }
  
  
  @Test
  @Order(4)
  @DisplayName("Test squares: number = random")
  void squaresInputRandom() {
    int number = NumbersApi.Limits.MIN.getValue() 
        + new Random().nextInt(NumbersApi.Limits.MAX.getValue() - 1);
    List<Integer> squares = numbersService.squares(number);

    boolean allInRange = squares.stream().allMatch(n -> 
        n >= 0 && n <= NumbersApi.Limits.MAX.getValue() * NumbersApi.Limits.MAX.getValue());

    assertEquals(squares.size(), number + 1);
    assertEquals(allInRange, true);
  }

  @Test
  @Order(5)
  @DisplayName("Test squares: number = invalid negative")
  void squaresInputInvalidNegative() {
    int number = -1;

    assertThrows(IllegalArgumentException.class, () -> {
      numbersService.squares(number);  });
  }

  @Test
  @Order(5)
  @DisplayName("Test squares: number = invalid max exceed")
  void squaresInputInvalidTooMuch() {
    int number = NumbersApi.Limits.MAX.getValue() + 1;

    assertThrows(IllegalArgumentException.class, () -> {
      numbersService.squares(number);  });
  }
  
  @AfterAll
  public static void tearDownAll() {
    logger.trace("AfterAll");
  }
  
}
