package com.chejet.cloud.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.chejet.cloud.annotation.Slog;
import com.chejet.cloud.model.SysUser;
import com.chejet.cloud.po.User;
import com.chejet.cloud.service.IUserServiceTest;

/**
 * 
 * @author ansen.zhu@carlt.com.cn
 * @date 2018/12/05
 */
@RestController
public class UserControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	@Autowired
	IUserServiceTest userService;

	@Value("${server.port}")
	String port;

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	public static final String KEY_USER = "cloud_user";

	@Slog(description="用户登录", modelName="用户模块", type="login")
	@PostMapping(value = "/login", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object login(HttpServletRequest request, User user) throws Exception{		
		HttpSession session = request.getSession();       
		session.setAttribute(KEY_USER, user);
//		logger.info("{} 登录系统成功!", user.getUsername());
//		user.setUsername("aoptest");
		return user;
	}
	
	@GetMapping(value = { "/", "/index" })
	public Object index() {
		SysUser user = userService.getUserById(Long.valueOf(1));
		return "userName:" + user.getUsername() + "<br>password:" + user.getPassword() + "port:" + port;
	}

	@PostMapping(value = "/addUser", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object addUser(@RequestBody SysUser user) {
		SysUser user2 = userService.addUser(user);
		return user2.getUsername();
	}

	@PostMapping(value = "/listUsers", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Object listUsers() {
		HashMap<String, Object> map = new HashMap<String, Object>(64);
		List<SysUser> list = userService.listUsers(map);
		return list;
	}

	@PostMapping(value = "/listUsersPage")
	public Object listUsersPage() {
		HashMap<String, Object> map = new HashMap<String, Object>(64);
		List<SysUser> list = userService.listUsersPage(map);
		return list;
	}

	@PostMapping("/get/{id}")
	public SysUser test(@PathVariable Integer id) {
		RestTemplate client = restTemplateBuilder.build();
		String url = "http://localhost:8080/user/{id}";
		SysUser user = client.getForObject(url, SysUser.class, id);
		return user;
	}

	@GetMapping("/user/{id}")
	public SysUser getUserById(@PathVariable Long id) {
		SysUser user = userService.getUserById(id);
		return user;
	}

	@GetMapping("/addUserClient")
	@ResponseBody
	public String addUserClient() {
		RestTemplate client = restTemplateBuilder.build();
		String uri = "http://localhost:8080/addUser";
		SysUser user = new SysUser();
		user.setUsername("hpp");
		user.setPassword("123456");
		String ret = client.postForObject(uri, user, String.class);
		return ret;
	}
}
