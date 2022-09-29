package com.boot.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.boot.domain.Board;
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
	
	// 인증 상태 유지하기
	@ModelAttribute("member")
	public Member setMember() {
		log.info("로그인 컨트롤러 맴버");
		return new Member();
	}
	
	// 로그인 폼 페이지 요청
	@GetMapping("/login")
	public String loginView(@ModelAttribute("member") Member member) {
		if(member.getName() != null) {
			return "redirect:";
		}
		return "login";
	}
	
	// 로그인 인증 처리
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = service.getMember(member);
		if(findMember != null
				&& findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember);	// 세션 발급
			return "redirect:";
		}else {
			int error = 1;
			model.addAttribute("error", error);
			return "login";
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
	
	@GetMapping("/signUp")
	public String signUpView(@ModelAttribute("member") Member member) {
		if(member.getId() != null) {
			return "redirect:";
		}
		else {
			return "/signUp";
		}
	}
	
	@PostMapping("/signUp")
	public String signUp(Member member, SessionStatus status) {
		service.addMember(member);
		status.setComplete();
		
		return "redirect:";
	}
	
	// 회원 탈퇴
	@GetMapping("/deleteMember")
	public String deleteMember(Member member, SessionStatus status) {
		service.deleteMember(member);
		status.setComplete();
		return "redirect:";
	}
}
