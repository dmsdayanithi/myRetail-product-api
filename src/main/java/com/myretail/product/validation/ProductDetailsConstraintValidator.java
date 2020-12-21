package com.myretail.product.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

import com.myretail.product.model.ProductDetailsRequest;

public class ProductDetailsConstraintValidator
		implements ConstraintValidator<ProductDetailsConstraint, ProductDetailsRequest> {
	@Override
	public void initialize(ProductDetailsConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(final ProductDetailsRequest productDetailsRequest,
			final ConstraintValidatorContext constraintValidatorContext) {

		return productDetailsRequest != null && productDetailsRequest.getProductPrice() != null
				&& !StringUtils.isEmpty(productDetailsRequest.getProductPrice().getCurrencyCode())
				&& productDetailsRequest.getProductPrice().getValue() != null
				&& productDetailsRequest.getProductPrice().getValue() > 0.0;
	}
}
