package com.boot.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.BoardDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.entity.Search;
import com.boot.repository.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;
	
	// 게시글 등록
	@Override
	public Long register(BoardDto dto) {
		Board board = dtoToEntity(dto);
		boardRepo.save(board);
		return board.getBno();
	}

	// 게시글 목록 보기
	@Override
	public PageResultDto<BoardDto, Object[]> getList(PageRequestDto pageRequestDto) {
		
		Function<Object[], BoardDto> fn = (en -> entityToDto((Board)en[0], (Member)en[1], (Long)en[2]));
		
		return null;
	}

	@Override
	public BoardDto get(Long bno) {
		
		return null;
	}
	
}
