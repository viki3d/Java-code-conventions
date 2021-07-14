package com.viki3d.javacodeconventions.front.api;

import java.util.List;

/**
 * Generates all numbers in interval [0, number] (twice inclusive), then apply operation over them.
 * 
 * @author viki3d
 * @version 1
 */
public interface NumbersApi {
	
	/**
	 * Defines the allowed limits for the input {@code int} parameter of every request. 
	 */
	public enum Limits {
		MIN(0), MAX(10);
				
		private int value;
		
		private Limits(int limit) {
			this.value = limit;
		}
		
		public int getValue() {
			return this.value;
		}
	}

	/**
	 * @param number Path {@code int} parameter; The upper boundary of the interval [0, number].
	 * 
	 * @return Squares of all numbers in the interval [0, number] in JSON.
	 * 
	 * @throws IllegalArgumentException when:
	 * <ul>
	 *     <li>{@code number} is not parsable {@code int}</li> 
	 *     <li>{@code number} doesn't obey the limits [{@link NumbersApi.Limits#MIN}, 
	 *     {@link NumbersApi.Limits#MAX}]</li>
	 * </ul>
	 * 
	 * @see <a href="http://localhost:8080/api/v1/numbers/squares/5" target="_blank">test link</a>
	 * 
	 * @since v1
	 */
	public List<Integer> squares(int number);
	
	/**
	 * @param number Path {@code int} parameter; The upper boundary of the interval [0, number].
	 * 
	 * @return All odd numbers in the interval [0, number] in JSON.
	 * 
	 * @throws IllegalArgumentException when:
	 * <ul>
	 *     <li>{@code number} is not parsable {@code int}</li> 
	 *     <li>{@code number} doesn't obey the limits [{@link NumbersApi.Limits#MIN}, 
	 *     {@link NumbersApi.Limits#MAX}]</li>
	 * </ul>
	 * 
	 * @see <a href="http://localhost:8080/api/v1/numbers/odds/5" target="_blank">test link</a>
	 * 
	 * @since v1
	 */
	public List<Integer> odds(int number);
	
	/**
	 * @param number Path {@code int} parameter; The upper boundary of the interval [0, number].
	 * 
	 * @return The sum of numbers in the interval [0, number] in JSON.
	 * 
	 * @throws IllegalArgumentException when:
	 * <ul>
	 *     <li>{@code number} is not parsable {@code int}</li> 
	 *     <li>{@code number} doesn't obey the limits [{@link NumbersApi.Limits#MIN}, 
	 *     {@link NumbersApi.Limits#MAX}]</li>
	 * </ul>
	 *
	 * @see <a href="http://localhost:8080/api/v1/numbers/sum/5" target="_blank">test link</a>
	 * 
	 * @since v1
	 */
	public int sum(int number);
	
}
