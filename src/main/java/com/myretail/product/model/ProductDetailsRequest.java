package com.myretail.product.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.myretail.product.validation.ProductDetailsConstraint;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * This class is used to as RequestBody for updating the Product Details.
 *
 * @author Dayanithi Devarajan
 */
@ProductDetailsConstraint
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsRequest {

	private int id;
	private ProductPrice productPrice;

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(final int id) {
		this.id = id;
	}

	@JsonGetter("current_price")
	public ProductPrice getProductPrice() {
		return productPrice;
	}

	@JsonSetter("current_price")
	public void setProductPrice(final ProductPrice productPrice) {
		this.productPrice = productPrice;
	}
}
