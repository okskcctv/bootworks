package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.domain.Member;
import com.boot.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@SessionAttributes("member")
@Controller
@Slf4j
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	// 인증 상태 유지하기
	@ModelAttribute("member")
	public Member setMember() {
		log.info("맴버 컨트롤러 맴버");
		return new Member();
	}
	
	@GetMapping("/getMemberList")
	public String getMemberList(@ModelAttribute("member") Member member, Model model) {
		if(member.getRole() == null) {
			return "redirect:login";
		}
		else if(member.getRole().equals("ROLE_ADMIN")) {
			List<Member> memberList = service.getMemberList();
			model.addAttribute("memberList", memberList);
			
			return "getMemberList";
		}
		else if(member.getRole() != null) {
			return "redirect:";
		}
		else {
			return "redirect:login";
		}
	}
	
	@GetMapping("/getMember")
	public String getMember(String id, Model model) {
		Member member = service.viewMember(id);
		model.addAttribute(member);
		return "getMember";
	}
	
	@PostMapping("updateMember")
	public String updateMember(Member member) {
		service.updateMember(member);
		
		return "redirect:";
	}
	
	
}
