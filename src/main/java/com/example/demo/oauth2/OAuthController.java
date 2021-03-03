package com.example.demo.oauth2;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthController {

	@GetMapping("/ouath2")
	public String message(Principal principal) {
		return "Hi" + principal.getName() + " welcome to oauth page";
	}
	
}
