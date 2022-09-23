package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.boot.domain.Member;
import com.boot.service.MemberService;

import lombok.extern.slf4j.Slf4j;

// Model 객체 - 'member'를 자동으로 세션에 저장함
@SessionAttributes("member")
@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private MemberService service;
	
	// 로그인 폼 페이지 요청
	@GetMapping("/login")
	public void loginView() {
		
	}
	
	// 로그인 인증 처리
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = service.getMember(member);
		if(findMember != null
				&& findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);	// 세션 발급
			return "redirect:getBoardList";
		}else {
			return "redirect:login";
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/logout")
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
		session.invalidate();
		log.info("로그아웃");
		return "redirect:";	// 경로가 공백이면 '/' 경로와 같음
	}
}
