package com.example.demo.security.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("loginBrowser")
	public String login() {
		return "login";
	}
	
	@GetMapping("logout")
	public String logout() {
		return "login";
	}	
	
	@GetMapping("register")
	public String register() {
		return "register";
	}
	
}
