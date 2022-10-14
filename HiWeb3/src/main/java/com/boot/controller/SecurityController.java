package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/member/logout")
	public void logout() {
		
	}
	
	@GetMapping("/member/login")
	public void login() {
		
	}
}
