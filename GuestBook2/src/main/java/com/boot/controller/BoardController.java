package com.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/board/")
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public void list(PageRequestDTO requestDTO, Model model) {
		model.addAttribute("result", boardService.getList(requestDTO));
	}
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(BoardDTO dto, RedirectAttributes redirectAttributes) {
		Long bno = boardService.resister(dto);
		
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/read")
	public void read(@ModelAttribute("requestDTO") PageRequestDTO requestDTO,
			Long bno, Model model) {
		BoardDTO boardDTO = boardService.get(bno);
		
		model.addAttribute("dto", boardDTO);
	}
	
	@GetMapping("/deleteBoard")
	public String deleteBoard(Long bno, RedirectAttributes redirectAttributes){
		boardService.removeWithReplies(bno);
		
		redirectAttributes.addFlashAttribute("msg", bno);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/updateBoard")
	public String updateBoard(BoardDTO dto,
			@ModelAttribute("requestDTO") PageRequestDTO requestDTO,
			RedirectAttributes redirectAttributes) {
		boardService.updateBoard(dto);
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("type", requestDTO.getType());
		redirectAttributes.addAttribute("keyword", requestDTO.getKeyword());
		
		redirectAttributes.addAttribute("bno", dto.getBno());
		
		return "redirect:/board/list";
	}
}
