package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.entity.Role;
import com.boot.service.MemberService;

@RequestMapping("/member/")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/signup")
	public void signupView() {
		
	}
	
	@PostMapping("/signup")
	public String signup(Member member) {
		service.signup(member);
		return "redirect:login";
	}
	
	@GetMapping("/userInfo")
	public void userInfo(Model model,
			@AuthenticationPrincipal SecurityUser principal) {
		Member member = principal.getMember();
		model.addAttribute(member);
	}
	
	@PostMapping("/updateMember")
	public String updateMember(Member member) {
		
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		
		service.update(member);
		return "redirect:/member/logout";
	}
	
	@GetMapping("/deleteMember")
	public String deleteMember(@AuthenticationPrincipal SecurityUser principal) {
		Member member = principal.getMember();
		service.delete(member);
		
		return "redirect:/member/logout";
	}
	
}
