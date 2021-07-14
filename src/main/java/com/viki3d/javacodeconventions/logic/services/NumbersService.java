package com.viki3d.javacodeconventions.logic.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.viki3d.javacodeconventions.front.api.NumbersApi;

@Service
public class NumbersService implements NumbersApi {

	private final Logger logger = LoggerFactory.getLogger(NumbersService.class.getName());
	
	/**
	 * Verifies the input parameter.
	 * 
	 * <p>
	 * <i>Javadoc is not required for private methods (Non-required). However, documenting a private
	 * methods is recommended and is referred as 'desired javadoc'.</i> 
	 * </p>
	 * 
	 * @param number 
	 * 
	 * @throws IllegalArgumentException when:
	 * <ul>
	 *     <li>{@code number} is not parsable {@code int}</li> 
	 *     <li>{@code number} doesn't obey the limits [{@link NumbersApi.Limits#MIN}, 
	 *     {@link NumbersApi.Limits#MAX}]</li>
	 * </ul>
	 * 
	 * @see {@code NumbersApi.squares}
	 * @see {@code NumbersApi.odds}
	 * @see {@code NumbersApi.sum}
	 */
	private void verifyLimits(int number) {
		if (number < Limits.MIN.getValue() || number > Limits.MAX.getValue()) {
			logger.error("Invalid input: " + number);
			throw new IllegalArgumentException("Input '" + number + "' is not in expected limits [" 
					+ Limits.MIN.getValue() + ", " + Limits.MAX.getValue() + "]");
		}
	}
	
	public List<Integer> squares(int number) {
		logger.trace(".squares(" + number + ")");		
		verifyLimits(number);
		
		List<Integer> squares = IntStream.rangeClosed(0, number)
				.map( n -> (int) Math.pow(n, 2) )
				.boxed()
				.collect(Collectors.toList());
		
		return squares;
	}
	
	public List<Integer> odds(int number) {
		logger.trace(".odds(" + number + ")");		
		verifyLimits(number);
		
		List<Integer> odds = IntStream.rangeClosed(0, number)
				.filter( n -> n % 2 == 1 )
				.boxed()
				.collect(Collectors.toList());
		
		return odds;
	}	
	
	public int sum(int number) {
		logger.trace(".sum(" + number + ")");
		verifyLimits(number);
		
		int sum = IntStream.rangeClosed(0, number).reduce(0, Integer::sum);
		
		return sum;
	}	
	
}
