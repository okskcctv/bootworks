package com.boot.service;

import java.util.function.Function;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.dto.BoardDTO;
import com.boot.dto.PageRequestDTO;
import com.boot.dto.PageResultDTO;
import com.boot.entity.Board;
import com.boot.entity.Member;
import com.boot.repository.BoardRepository;
import com.boot.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepo;
	
	private final ReplyRepository replyRepo;
	
	@Override
	public Long resister(BoardDTO dto) {
		Board board = dtoToEntity(dto);
		boardRepo.save(board);
		
		return board.getBno();
	}
	
	// 게시물 목록 보기
	@Override
	public PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
		
		// entityToDTO 호출
		Function<Object[], BoardDTO> fn = (en ->
			entityToDto((Board)en[0], (Member)en[1], (Long)en[2]));
		
		//Pageable pageable = pageRequestDTO.getPageable(Sort.by("bno").descending());
		//Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
		
		Page<Object[]> result = boardRepo.searchPage(
						pageRequestDTO.getType(),
						pageRequestDTO.getKeyword(),
						pageRequestDTO.getPageable(Sort.by("bno").descending()));
		
		return new PageResultDTO<>(result, fn);
	}
	
	@Override
	public BoardDTO get(Long bno) {
		
		Object result = boardRepo.getBoardByBno(bno);
		
		Object[] arr = (Object[])result;
		
		return entityToDto((Board)arr[0], (Member)arr[1], (Long)arr[2]);
	}
	
	@Transactional
	@Override
	public void removeWithReplies(Long bno) {	// 삭제 기능 구현, 트랜잭션 추가
		
		// 댓글 부터 삭제
		replyRepo.deleteByBno(bno);
		
		boardRepo.deleteById(bno);
		
	}
	
	@Transactional
	@Override
	public void updateBoard(BoardDTO boardDTO) {
		
		Board board = boardRepo.findById(boardDTO.getBno()).get();
		
		if(board != null) {
			
			board.changeTitle(boardDTO.getTitle());
			board.changeContent(boardDTO.getContent());
			
			boardRepo.save(board);
		}
		
	}
	
	
}
