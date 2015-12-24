package com.sweet.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sweet.queue.bean.User;
import com.sweet.queue.service.redisService;

@Controller
@RequestMapping("/test")
public class HelloController {
	
	@Autowired
	private redisService redisService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String helloSpringMvc(Model model){
		 model.addAttribute("hello", "hello mvc");
	     return "helloSpring";
	}
	
	@RequestMapping(value = "/redis", method = RequestMethod.GET)
	public String redisTest(Model model) throws Exception{
		boolean result = redisService.insert();
		model.addAttribute("hello", result);
	    return "helloSpring";	
	}
	
	@RequestMapping(value = "/redispub", method = RequestMethod.GET)
	public String redisPubTest(Model model) throws Exception{
		User user = new User();
		user.setUserId("sweet");
		user.setUserName("shiwei");
		redisService.sentMessage("sweet", user);
	    return "helloSpring";	
	}
}
