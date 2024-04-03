package com.springsec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
	
	@GetMapping("/user")
	public String get(Authentication authentication) {
		return "welcome"+authentication.getName()+"to spring security";
	}

}
