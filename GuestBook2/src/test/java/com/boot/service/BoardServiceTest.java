package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.BoardDTO;

@SpringBootTest
public class BoardServiceTest {
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testResister() {
		BoardDTO dto = BoardDTO.builder()
				.title("테스트 제목")
				.content("테스트 내용")
				.writerEmail("user50@naver.com")
				.build();
		
		boardService.resister(dto);
	}
	
}
