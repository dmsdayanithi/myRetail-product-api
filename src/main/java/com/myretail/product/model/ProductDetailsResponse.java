package com.myretail.product.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsResponse {

	private int id;
	private String name;
	private ProductPrice productPrice;

	@JsonGetter("id")
	public int getId() {
		return id;
	}

	@JsonSetter("id")
	public void setId(final int id) {
		this.id = id;
	}

	@JsonGetter("name")
	public String getName() {
		return name;
	}

	@JsonSetter("name")
	public void setName(final String name) {

		this.name = name;
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
