package com.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.domain.QBoard;
import com.boot.domain.Search;
import com.boot.persistence.BoardRepository;
import com.querydsl.core.BooleanBuilder;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;
	
	// 게시글 목록 보기
	@Override
	public Page<Board> getBoardList(Search search) {
		BooleanBuilder builder = new BooleanBuilder();
		QBoard qboard = QBoard.board;
		
		if(search.getSearchCondition().equals("TITLE")) {
			builder.and(qboard.title.like("%" + search.getSearchKeyword() + "%"));
		}else if(search.getSearchCondition().equals("CONTENT")) {
			builder.and(qboard.content.like("%" + search.getSearchKeyword() + "%"));
		}
		
		// 10개의 데이터를 내림차순 정렬(페이지 번호, 검색 데이터 개수, 내림차순)
		Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
		return boardRepo.findAll(builder, pageable);
	}

	// 게시글 등록
	@Override
	public void insertBoard(Board board) {
		boardRepo.save(board);
	}

	// 게시글 상세 조회
	@Override
	public Board getBoard(Long seq) {
		return boardRepo.findById(seq).get();
	}

	// 게시글 수정
	@Override
	public void updateBoard(Board board) {
		boardRepo.save(board);
	}

	// 게시글 삭제
	@Override
	public void deleteBoard(Board board) {
		boardRepo.delete(board);
	}

	// 조회수
	@Override
	public void updateCount(Long seq) {
		boardRepo.updateCount(seq);
	}
	
}
