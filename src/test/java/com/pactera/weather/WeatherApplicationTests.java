package com.pactera.weather;

import com.pactera.weather.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApplication.class)
//测试环境使用，用来表示测试环境使用的ApplicationContext将是WebApplicationContext类型的
@WebAppConfiguration
public class WeatherApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(WeatherApplicationTests.class);

	@Autowired
	private WebApplicationContext context;

	private MockMvc mockMvc;

	@Autowired
	private DemoService demoService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();// 建议使用这种
	}

	/**
	 * 测试 Controller 接口
	 * @throws UnsupportedEncodingException
	 * @throws Exception
	 */
	@Test
	public void testController() throws UnsupportedEncodingException, Exception {
		// 请求的url,请求的方法是get
		String responseString = mockMvc.perform(MockMvcRequestBuilders.get("/queryCurrentWeather/101070201")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print())
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符

		LOG.info(responseString);
	}
}

