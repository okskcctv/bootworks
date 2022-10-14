package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.boot.config.SecurityUser;
import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.service.BoardService;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> boardList = service.getBoardList();
		model.addAttribute("boardList", boardList);
		return "board/getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		Board board = service.getBoard(seq);
		model.addAttribute("board", board);
		return "board/getBoard";
	}
	
	@GetMapping("/insertBoard")
	public void insertBoardView() {
		
	}
	
	@PostMapping("/insertBoard")
	public String insertBoard(Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		service.insertBoard(board);
		return "redirect:/board/getBoardList";
	}
	
	@GetMapping("/updateBoard")
	public String updateBoard(Board board,
			@AuthenticationPrincipal SecurityUser principal) {
		board.setMember(principal.getMember());
		service.updateBoard(board);
		return "redirect:/board/getBoardList";
		
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Long seq) {
		Board board = service.getBoard(seq);
		service.deleteBoard(board);
		return "redirect:/board/getBoardList";
	}
	
}
