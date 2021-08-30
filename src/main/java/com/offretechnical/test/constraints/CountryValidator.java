package com.offretechnical.test.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<Country, String> {
	@Override
	public boolean isValid(final String valueToValidate, final ConstraintValidatorContext context) {
		return "France".equalsIgnoreCase(valueToValidate);
	}
}
