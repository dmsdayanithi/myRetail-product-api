package com.myretail.product.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.product.model.ProductDetailsRequest;
import com.myretail.product.model.ProductDetailsResponse;
import com.myretail.product.service.ProductDetailsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/v1")
public class ProductDetailsController {

	private static final Logger log = LoggerFactory.getLogger(ProductDetailsController.class);

	@Autowired
	ProductDetailsService productDetailsService;

	@GetMapping(path = "/products/{id}", produces = "application/json")
	@ApiOperation(value = "Retrieves the product details for the given product Id.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "ProductDetail Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ProductDetailsResponse getProductDetails(@PathVariable Integer id, HttpServletResponse response) throws Exception {
		log.info("Incoming get request, id={}", id);

		final ProductDetailsResponse productDetailsResponse = productDetailsService.getProductDetails(id);
		if (productDetailsResponse == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		return productDetailsResponse;
	}

	@PutMapping(path = "/products/{id}", consumes = "application/json")
	@ApiOperation(value = "Updates the details for the given product.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Details updated Successfully"),
			@ApiResponse(code = 400, message = "Invalid Input"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ProductDetailsResponse putProductDetails(@PathVariable Integer id,
			@Valid @RequestBody ProductDetailsRequest productDetailsRequest, HttpServletResponse response)
			throws Exception {
		log.info("Incoming put request, id={}", id);

		if (productDetailsRequest.getId() != id) {
			log.info("Invalid data, id={}", id);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		final ProductDetailsResponse productDetailsResponse = productDetailsService.updateProductDetails(id,
				productDetailsRequest);
		if (productDetailsResponse != null) {
			response.setStatus(HttpServletResponse.SC_OK);
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		log.info("Updated product detail for id={}", id);
		return productDetailsResponse;
	}
}
