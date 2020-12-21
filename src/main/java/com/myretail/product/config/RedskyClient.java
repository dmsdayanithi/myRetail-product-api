package com.myretail.product.config;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.myretail.product.model.Product;
import com.myretail.product.model.RedskyProductResponse;

@Component
public class RedskyClient {

	private static final Logger log = LoggerFactory.getLogger(RedskyClient.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${product.endpoint}")
	private String endpoint;

	@Value("${redsky.host}")
	private String host;

	public CompletableFuture<ResponseEntity<RedskyProductResponse>> getProductById(Integer id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Product> entity = new HttpEntity<>(headers);
		ResponseEntity<RedskyProductResponse> responseEntity = null;

		String url = String.format(host + endpoint, id);
		long startTime = System.currentTimeMillis();
		try {
			responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, RedskyProductResponse.class);

		} catch (HttpClientErrorException ex) {
			log.error("Product service returned client error,id={}, status={}", id, ex.getStatusCode());
		} catch (HttpServerErrorException ex) {
			log.error("Product service returned server error, id={}, status={}", id, ex.getStatusCode());
		} catch (Exception ex) {
			log.error("Exception occurred while getting product data, id={}", id, ex);
		} finally {
			long duration = System.currentTimeMillis() - startTime;
			log.info("External service call completed; duration={}", duration);
		}

		return CompletableFuture.completedFuture(responseEntity);
	}
}
