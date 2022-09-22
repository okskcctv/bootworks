package com.boot.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.boot.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{
	
	// 쿼리 메소드 - 글 제목 검색
	List<Board> findByTitle(String searchKeyword);
}
