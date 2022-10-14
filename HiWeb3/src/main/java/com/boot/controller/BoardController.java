package com.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.config.SecurityUser;
import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.service.BoardService;

import jdk.internal.org.jline.utils.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/board/")
@Controller
public class BoardController {
	
	@Autowired
	private final BoardService boardService;
	
	//게시글 목록 보기
	@GetMapping("/list")
	public String list(PageRequestDto pageRequestDto, Model model) {
		//PageResultDto<BoardDto, Object[]> result = boardService.getList(pageRequestDto);
		model.addAttribute("result", boardService.getList(pageRequestDto));
		return "board/list";
	}
	
	// 게시글 작성
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardDto boardDto, RedirectAttributes redirectAttributes) {
		Long bno = boardService.register(boardDto);
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:list";
	}
	
	
	/*@GetMapping("/getBoardList")
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
	}*/
	
}
