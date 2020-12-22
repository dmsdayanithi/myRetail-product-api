package com.myretail.product.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This class defines the item details on Product.
 *
 * @author Dayanithi Devarajan
 */
public class Item {

	private ProductDescription productDescription;

	@JsonGetter("product_description")
	public ProductDescription getProductDescription() {
		return productDescription;
	}

	@JsonSetter("product_description")
	public void setProductDescription(final ProductDescription productDescription) {
		this.productDescription = productDescription;
	}
}
