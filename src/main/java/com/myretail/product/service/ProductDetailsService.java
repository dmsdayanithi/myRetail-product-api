package com.myretail.product.service;

import com.myretail.product.model.ProductDetailsRequest;
import com.myretail.product.model.ProductDetailsResponse;

public interface ProductDetailsService {
    ProductDetailsResponse getProductDetails(Integer id) throws Exception;
    ProductDetailsResponse updateProductDetails(Integer id, ProductDetailsRequest productDetailsRequest) throws Exception;
}
