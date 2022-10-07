package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.dto.PageRequestDto;
import com.boot.service.GuestBookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/guestBook/")
@Controller
public class GuestBookController {
	
	private final GuestBookService service;
	
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		model.addAttribute("result", service.getList(pageRequestDto));
		return "/guestBook/list";
	}
}
