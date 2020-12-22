package com.myretail.product.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * This is a constraint used to validate ProductDetailRequest bean.
 *
 * @author Dayanithi Devarajan
 */
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductDetailsConstraintValidator.class)
@Documented
public @interface ProductDetailsConstraint {

	String message() default "Invalid product detail";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
