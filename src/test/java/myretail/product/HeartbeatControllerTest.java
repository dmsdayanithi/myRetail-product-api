package myretail.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.myretail.product.MyRetailProductApiApplication;
import com.myretail.product.controller.HeartbeatController;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = MyRetailProductApiApplication.class)
@WebMvcTest(value = HeartbeatController.class)
public class HeartbeatControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private HeartbeatController tested;
	
	@Test
	public void validateHeartBeat() throws Exception {
		ReflectionTestUtils.setField(tested, "applicationName", "my-retail-product");
		ReflectionTestUtils.setField(tested, "applicationVersion", "1");
		String expectedJsonValue = "{\"name\": \"my-retail-product\", \"version\": \"1\"}";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/heartbeat");

		MvcResult result = mvc.perform(requestBuilder).andReturn();

		JSONAssert.assertEquals(expectedJsonValue, result.getResponse().getContentAsString(), false);
	}
}
