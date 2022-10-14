package com.boot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.dto.ReplyDTO;
import com.boot.service.ReplyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/replies/")
@RestController
public class ReplyController {
	
	private final ReplyService replyService;
	
	// 댓글 목록
	// @PathBariable - 경로를 변수로 처리해줌
	@GetMapping(value = "/board/{bno}",
					produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ReplyDTO>> getListByBoard(
					@PathVariable("bno") Long bno){
		List<ReplyDTO> replyDTO = replyService.getList(bno);
		return new ResponseEntity<>(replyDTO, HttpStatus.OK);
	}
	
	// 댓글 쓰기
	// @RequestBody : 데이터를 자동으로 해당 타입의 객체로 매핑함
	@PostMapping("")
	public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO){
		Long rno = replyService.register(replyDTO);
		// http 연결에 성공하면 rno를 보내줌
		return new ResponseEntity<>(rno, HttpStatus.OK);
	}
}
