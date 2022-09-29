package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.service.BoardService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@SessionAttributes("member")
@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 인증 상태 유지하기
	@ModelAttribute("member")
	public Member setMember() {
		log.info("보드 컨트롤러 맴버");
		return new Member();
	}
	
	@GetMapping("/")
	public String home() {
		return "/index";
	}
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "getBoardList";
	}
	
	// 게시글 등록 폼 요청
	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		// 로그인 하지 않은 경우 - 로그인 페이지로 이동
		if(member.getId() == null) {
			return "redirect:login";
		}
		return "insertBoard";
	}
	
	// 게시글 등록 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board) {
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	// 게시글 상세 조회
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCnt(seq);	// 조회수 증가
		Board board = service.getBoard(seq);	// 상세 보기 처리
		model.addAttribute(board);
		return "getBoard";
	}
	
	// 게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
	
	// 게시글 수정
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		service.updateBoard(board);
		return "redirect:getBoardList";
	}
}
