package com.viki3d.javacodeconventions.front.controllers.rest;

import com.viki3d.javacodeconventions.front.api.NumbersApi;
import com.viki3d.javacodeconventions.logic.services.NumbersService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The front controller, servicing {@code NumberApi}.
 *
 * @author viki3d
 */
@RestController
@RequestMapping("/api/v1/numbers")
public class NumbersRestController implements NumbersApi {
  
  private final Logger logger = LoggerFactory.getLogger(NumbersRestController.class.getName());
  
  @Autowired
  private NumbersService numbersService;

  @Override
  @GetMapping(path = "/squares/{number}", produces = "application/json")
  public List<Integer> squares(@PathVariable("number") int number) {
    logger.trace(".squares(" + number + ")");
    return numbersService.squares(number);
  }
  
  @Override
  @GetMapping(path = "/odds/{number}", produces = "application/json")
  public List<Integer> odds(@PathVariable("number") int number) {
    logger.trace(".odds(" + number + ")");
    return numbersService.odds(number);
  }
  
  @Override
  @GetMapping(path = "/sum/{number}", produces = "application/json")
  public int sum(@PathVariable("number") int number) {
    logger.trace(".sum(" + number + ")");
    return numbersService.sum(number);
  }
  
}
