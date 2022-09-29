package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	// 로그인
	@GetMapping("/system/login")
	public void login() {}
	
	// 접근 권한 없음 페이지
	@GetMapping("/system/ccessDenied")
	public void accessDenied() {}
	
	// 로그아웃
	@GetMapping("/system/logout")
	public void logout() {}
	
	// 관리자 페이지
	@GetMapping("/admin/adminPage")
	public void admin() {}
}
