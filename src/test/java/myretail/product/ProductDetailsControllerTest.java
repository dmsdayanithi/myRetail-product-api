package myretail.product;

import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myretail.product.MyRetailProductApiApplication;
import com.myretail.product.controller.ProductDetailsController;
import com.myretail.product.model.ProductDetailsResponse;
import com.myretail.product.model.ProductPrice;
import com.myretail.product.service.ProductDetailsService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyRetailProductApiApplication.class)
@WebMvcTest(value = ProductDetailsController.class)
@Execution(ExecutionMode.CONCURRENT)
public class ProductDetailsControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductDetailsService productDetailsService;

	@Test
	public void validateGetProductDetails() throws Exception {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(13860428,
				"The Big Lebowski (Blu-ray) (Widescreen)", new ProductPrice(13.49, "USD"));
		String expectedJsonValue = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

		Mockito.when(productDetailsService.getProductDetails(Mockito.anyInt())).thenReturn(productDetailsResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/products/13860428")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(expectedJsonValue, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void validateInValidGetProductDetails() throws Exception {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(13860428, null, null);
		String expectedJsonValue = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

		Mockito.when(productDetailsService.getProductDetails(Mockito.anyInt())).thenReturn(productDetailsResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/products/13860428")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		JSONAssert.assertNotEquals(expectedJsonValue, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void validatePutProductDetails() throws Exception {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(13860428,
				"The Big Lebowski (Blu-ray) (Widescreen)", new ProductPrice(13.49, "USD"));
		String inputProductDetailsRequest = "{\"id\":13860428,\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";
		String expectedJsonValue = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

		Mockito.when(productDetailsService.updateProductDetails(Mockito.anyInt(), Mockito.any()))
				.thenReturn(productDetailsResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/products/13860428")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(inputProductDetailsRequest);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(expectedJsonValue, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void validateInValidPutProductDetails() throws Exception {
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse(13860428, null, null);
		String inputProductDetailsRequest = "{\"id\":13860428,\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";
		String expectedJsonValue = "{\"id\":13860428,\"name\":\"The Big Lebowski (Blu-ray) (Widescreen)\",\"current_price\":{\"value\": 13.49,\"currency_code\":\"USD\"}}";

		Mockito.when(productDetailsService.updateProductDetails(Mockito.anyInt(), Mockito.any()))
				.thenReturn(productDetailsResponse);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/products/13860428")
				.contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8").content(inputProductDetailsRequest);

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		JSONAssert.assertNotEquals(expectedJsonValue, result.getResponse().getContentAsString(), false);
	}
}
