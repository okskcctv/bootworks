package com.boot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.boot.domain.Board;
import com.boot.domain.Search;

public interface BoardService {
	
	List<Board> getBoardList();	// 게시글 목록
	
	// Page<Board> getBoardList(Search search);	// 게시글 목록(검색 처리)
	
	void insertBoard(Board board);	// 게시글 작성
	
	Board getBoard(Long seq);		// 게시글 상세보기
	
	void updateBoard(Board board);	// 게시글 수정
	
	void deleteBoard(Board board);	// 게시글 삭제
	
	void updateCount(Long seq);		// 조회수
}
