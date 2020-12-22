package com.myretail.product.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.product.config.RedskyClient;
import com.myretail.product.model.Product;
import com.myretail.product.model.ProductDetails;
import com.myretail.product.model.ProductDetailsRequest;
import com.myretail.product.model.ProductDetailsResponse;
import com.myretail.product.model.ProductPrice;
import com.myretail.product.model.RedskyProductResponse;
import com.myretail.product.repository.ProductDetailsRepository;
import com.myretail.product.service.ProductDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private RedskyClient redskyClient;

	@Autowired
	private ObjectMapper objectMapper;

	/**
     * This method will retrieve data from database and redsky rest service and make ProductDetailsResponse.
     * @param id
     * @return ProductDetailsResponse
     */
	@Override
	public ProductDetailsResponse getProductDetails(final Integer id) throws Exception {
		LOGGER.info("Retrieving product details for {} ", id);
		Product product = null;

		CompletableFuture<ResponseEntity<RedskyProductResponse>> futureResponse = redskyClient.getProductById(id);
		final ProductDetails productDetails = productDetailsRepository.findById(id).orElse(null);
		final ResponseEntity<RedskyProductResponse> responseEntity = futureResponse.get();
		if (responseEntity != null && responseEntity.hasBody()) {
			product = responseEntity.getBody().getProduct();
		}
		return constructProductDetailsResponse(id, productDetails, product);
	}

	/**
     * This method will update data into database.
     * @param id
     * @return ProductDetailsResponse
     */
	@Override
	public ProductDetailsResponse updateProductDetails(final Integer id,
			final ProductDetailsRequest productDetailsRequest) throws Exception {
		LOGGER.info("Saving the product details for {}", id);

		ProductDetails productDetails = new ProductDetails();
		productDetails.setId(productDetailsRequest.getId());
		try {
			productDetails.setCurrentPrice(objectMapper.writeValueAsString(productDetailsRequest.getProductPrice()));
		} catch (JsonProcessingException ex) {
			LOGGER.error("Error  parsing the current_price", ex);
			throw new Exception("Error  parsing the current_price");
		}
		final ProductDetails savedProductDetail = productDetailsRepository.save(productDetails);

		return constructProductDetailsResponse(id, savedProductDetail, null);
	}

	private ProductDetailsResponse constructProductDetailsResponse(final Integer id, final ProductDetails productDetails,
			final Product product) throws Exception {
		LOGGER.info("constructing the ProductDetailResponse for {}", id);
		ProductDetailsResponse productDetailsResponse = null;

		if (productDetails != null) {
			productDetailsResponse = new ProductDetailsResponse();
			productDetailsResponse.setId(id);
			try {
				productDetailsResponse
						.setProductPrice(objectMapper.readValue(productDetails.getCurrentPrice(), ProductPrice.class));
			} catch (JsonProcessingException ex) {
				LOGGER.error("Error  parsing the current_price", ex);
				throw new Exception("Error  parsing the current_price");
			}
		} else {
			LOGGER.info("Price information not found, id={}", id);
		}

		if (product != null) {
			if (productDetailsResponse == null) {
				productDetailsResponse = new ProductDetailsResponse();
				productDetailsResponse.setId(id);
			}
			if (product.getItem() != null && product.getItem().getProductDescription() != null) {
				productDetailsResponse.setName(product.getItem().getProductDescription().getTitle());
			}
		} else {
			LOGGER.info("Product not found in redsky, id={}", id);
		}
		return productDetailsResponse;
	}
}
