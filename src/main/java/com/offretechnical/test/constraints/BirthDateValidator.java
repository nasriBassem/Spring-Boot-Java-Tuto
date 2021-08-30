package com.offretechnical.test.constraints;

import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *  Date of birth validator
 *  
 * @author bn
 *
 */
public class BirthDateValidator implements ConstraintValidator<BirthDate, Date> {
	/**
	 * check if the person is an adult
	 */
	@Override
	public boolean isValid(final Date valueToValidate, final ConstraintValidatorContext context) {
		Calendar dateInCalendar = Calendar.getInstance();
		dateInCalendar.setTime(valueToValidate);
		return Calendar.getInstance().get(Calendar.YEAR) - dateInCalendar.get(Calendar.YEAR) >= 18;
	}
}
