package com.myretail.product.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsController.class);

	@Autowired
	private ProductDetailsService productDetailsService;

	/**
     * Retrieves the product details for the given product Id..
     *
     * <b>Note: </b>The response may have one of following.
     * <p>
     *    <ul>
     *        <li>ProductDetailsResponse data with status code 200 in case of successful retrieval.</li>
     *        <li>null value with status code of 404 in case of data not found in database.</li>
     *        <li>null value with status code of 500 in case of any exception.</li>
     *
     *    </ul>
     * </p>
     *
     * @param id       is a product id for which the product details has to be retrieved.
     * @param response is used to send http status codes based on data retrieval operation status.
     * @return ProductDetailsResponse for given id.
     */
	@GetMapping(path = "/products/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Retrieves the product details for the given product Id.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "ProductDetails Not Found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ProductDetailsResponse getProductDetails(final @PathVariable Integer id, final HttpServletResponse response) throws Exception {
		LOGGER.info("Incoming get request, id={}", id);

		final ProductDetailsResponse productDetailsResponse = productDetailsService.getProductDetails(id);
		if (productDetailsResponse == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}

		return productDetailsResponse;
	}

	/**
     * Updates the details for the given product.
     *
     * <b>Note: </b>The response may have one of following.
     * <p>
     *    <ul>
     *       <li>ProductDetailsResponse data with status code 200 in case of successful retrieval.</li>
     *       <li>null value with status code of 400 in case of invalid input.</li>
     *       <li>null value with status code of 500 in case of any exception.</li>
     *    </ul>
     * </p>
     *
     * @param id                    is a product id for which the product details has to be updated.
     * @param productDetailsRequest is the request body which has to be updated.
     * @param response              is used to send http status codes based on data retrieval operation status.
     * @return
     */
	@PutMapping(path = "/products/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Updates the details for the given product.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Product Details updated Successfully"),
			@ApiResponse(code = 400, message = "Invalid Input"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	ProductDetailsResponse putProductDetails(final @PathVariable Integer id,
			final @Valid @RequestBody ProductDetailsRequest productDetailsRequest, final HttpServletResponse response)
			throws Exception {
		LOGGER.info("Incoming put request, id={}", id);

		if (productDetailsRequest.getId() != id) {
			LOGGER.info("Invalid data, id={}", id);
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
		LOGGER.info("Updated product detail for id={}", id);
		return productDetailsResponse;
	}
}
