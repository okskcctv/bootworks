package com.boot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.GuestBookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.GuestBook;

@SpringBootTest
public class GuestBookServiceTest {
	
	@Autowired
	private GuestBookService service;
	
	// 새글 등록
	/*@Test
	public void testResister() {
		
		GuestBookDto guestBookDto = GuestBookDto.builder()
				.title("Sample title")
				.content("Sample Content")
				.writer("user0")
				.build();
		
		System.out.println(service.register(guestBookDto));
	}*/
	
	// 게시글 목록 보기
	@Test
	public void testList() {
		// 페이지 요청
		PageRequestDto pageRequestDto = PageRequestDto.builder()
				.page(1).size(10).build();
		
		// 페이지 결과
		PageResultDto<GuestBookDto, GuestBook> resultDto = 
				service.getList(pageRequestDto);
		System.out.println("prev: " + resultDto.isPrev());
		System.out.println("next: " + resultDto.isNext());
		System.out.println("total: " + resultDto.getTotalPage());
		
		// 반복 처리
		for(GuestBookDto guestBookDto : resultDto.getDtoList()) {
			System.out.println(guestBookDto);
		}
		
		System.out.println("==========================================");
		resultDto.getPageList().forEach(i -> System.out.println(i + " "));
		
	}
	
}
