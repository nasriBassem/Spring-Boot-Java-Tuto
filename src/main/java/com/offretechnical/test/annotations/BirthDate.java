package com.offretechnical.test.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.offretechnical.test.constraints.BirthDateValidator;
/**
 * Date of birth validation annotation
 * 
 * @author bn
 *
 */
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface BirthDate {

	String message() default "Only adult are allowed to create an account!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}