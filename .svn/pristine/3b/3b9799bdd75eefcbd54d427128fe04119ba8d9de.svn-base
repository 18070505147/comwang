package com.chejet.cloud;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.chejet.cloud.dao.FunctionPermissionPointMapper;
import com.chejet.cloud.vo.FunctionPermissionPointVO;
import org.beetl.sql.core.SQLManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.chejet.cloud.SpringBootApplicationTest;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTest {

	@Test
	public void contextLoads() {
	}

	/**
	 * 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。
	 */
	private MockMvc mockMvc;

	/**
	 * // 注入WebApplicationContext
	 */
	@Autowired
	private WebApplicationContext wac;

	/**
	 * 在测试开始前初始化工作
	 */
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	//@Test
	public void testAddUser() throws Exception {
		Map<String, Object> map = new HashMap<>(64);
		map.put("username", "test");
		map.put("password", "123");

		MvcResult result = mockMvc
				.perform(post("/addUser").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
				// 模拟向testRest发送get请求
				.andExpect(status().isOk())
				// 预期返回值的媒体类型text/plain;charset=UTF-8
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				// 返回执行请求的结果
				.andReturn();

		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void testLogin() throws Exception {
		Map<String, Object> map = new HashMap<>(64);
		map.put("username", "test");
		map.put("password", "123");
		map.put("id", 1070199672200495104L);

		MvcResult result = mockMvc
				.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(map)))
				// 模拟向testRest发送get请求
				.andExpect(status().isOk())
				// 预期返回值的媒体类型text/plain;charset=UTF-8
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				// 返回执行请求的结果
				.andReturn();

		System.out.println(result.getResponse().getContentAsString());
	}

	@Autowired
	private SQLManager sqlManager;



	@Test
	public void test1() throws Exception {
	}
}
