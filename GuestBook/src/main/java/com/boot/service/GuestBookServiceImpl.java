package com.boot.service;

import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.GuestBookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.GuestBook;
import com.boot.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor	// 생성자 주입(final 키워드 사용)
@Log4j2
@Service
public class GuestBookServiceImpl implements GuestBookService{

	private final GuestBookRepository repository;
	
	@Override
	public Long register(GuestBookDto dto) {
		log.info("DTO -----------------");
		log.info(dto);
		
		GuestBook entity = dtoToEntity(dto);	// dto를 엔티티로 변환 메서드 호출
		log.info(entity);
		
		repository.save(entity);
		
		return entity.getGno();
	}
	
	@Override
	public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto){
		Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
		Page<GuestBook> result = repository.findAll(pageable);
		Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
		return new PageResultDto<>(result, fn);
	}

}
