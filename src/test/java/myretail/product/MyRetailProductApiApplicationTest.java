package myretail.product;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.myretail.product.MyRetailProductApiApplication;
import com.myretail.product.controller.ProductDetailsController;
import com.myretail.product.service.ProductDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyRetailProductApiApplication.class)
@ContextConfiguration(classes = { ProductDetailsController.class, ProductDetailsService.class })
@AutoConfigureMockMvc
public class MyRetailProductApiApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getProductDetails() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/products/13860428").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("13860428"));
	}

	@Test
	public void getProductDetailsInvalidRequest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/products/abcd").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void getProductDetailsNoData() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/v1/products/12345").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void updateProductDetailsHappyPath() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/v1/products/13860428")
				.content("{\"id\":13860428,\"current_price\":{\"value\":24.49,\"currency_code\":\"USD\"}}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void updateProductDetailsInvalidData() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/v1/products/13860427")
				.content("{\"id\":13860428,\"current_price\":{\"value\":24.49,\"currency_code\":\"USD\"}}")
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isBadRequest());
	}
}
