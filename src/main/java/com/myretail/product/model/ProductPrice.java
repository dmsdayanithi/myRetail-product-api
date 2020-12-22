package com.myretail.product.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the price of a product.
 *
 * @author Dayanithi Devarajan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {

	private Double value;
	private String currencyCode;

	@JsonGetter("currency_code")
	public String getCurrencyCode() {
		return currencyCode;
	}

	@JsonSetter("currency_code")
	public void setCurrencyCode(final String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
