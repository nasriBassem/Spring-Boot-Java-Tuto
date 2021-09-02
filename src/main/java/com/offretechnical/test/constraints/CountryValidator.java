package com.offretechnical.test.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Country validator
 * 
 * @author bn
 *
 */
public class CountryValidator implements ConstraintValidator<Country, String> {

	public static final String FRANCE = "France";

	/**
	 * check if the person lives in France
	 */
	@Override
	public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
		return FRANCE.equalsIgnoreCase(valueToValidate);
	}
}
