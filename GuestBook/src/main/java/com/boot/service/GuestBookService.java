package com.boot.service;

import com.boot.dto.GuestBookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.GuestBook;

public interface GuestBookService {
	
	// 게시글 등록
	Long register(GuestBookDto dto);
	
	// 게시글 목록
	PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto);
	
	// 게시글 상세보기
	GuestBookDto read(Long gno);
	
	// 자바 8버전 부터 구체 메서드 사용 가능 (default 키워드로 가능)
	default GuestBook dtoToEntity(GuestBookDto dto) {
		GuestBook entity = GuestBook.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getTitle())
				.writer(dto.getWriter())
				.build();
		return entity;
	}
	
	// db의 entity를 화면에 보내주기 위해 dto 객체를 반환함
	default GuestBookDto entityToDto(GuestBook entity) {
		GuestBookDto dto = GuestBookDto.builder()
				.gno(entity.getGno())
				.title(entity.getTitle())
				.content(entity.getContent())
				.writer(entity.getWriter())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
}
