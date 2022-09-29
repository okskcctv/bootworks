package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.service.BoardService;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	// 게시글 목록
	@GetMapping("/getBoardList")
	public String getBoardList(Board board, Model model) {
		Page<Board> boardList = service.getBoardLsit(board);
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	// 게시글 등록 폼 요청
	@GetMapping("/insertBoard")
	public String insertBoardView() {
		return "board/insertBoard";
	}
	
	// 게시글 등록 처리
	@PostMapping("/insertBoard")
	public String insertBoard(Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		// 로그인 성공한 Member(회원) 객체를 가지고 있는 SecurityUser 객체를
		// 매개 변수로 받음. 연관된 Member 엔티티를 Board 엔티티에 설정함
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:getBoardList";
	}
	
	// 게시글 상세 조회
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		service.updateCount(seq);	// 조회수 증가
		Board board = service.getBoard(seq);
		model.addAttribute(board);
		return "board/getBoard";
	}
	
	// 게시글 삭제
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		service.deleteBoard(board);
		return "redirect:getBoardList";
	}
}
