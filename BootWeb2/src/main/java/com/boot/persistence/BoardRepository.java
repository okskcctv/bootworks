package com.boot.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.boot.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	// 조회수
	@Modifying	// 조회할때만 사용되는 함수
	@Query("UPDATE Board b SET b.cnt = b.cnt + 1 WHERE b.seq = :seq")
	void updateCnt(Long seq);
	
	// 제목에 특정 단어가 포함된 글 목록을 내림차순으로 조회
	@Query("SELECT b FROM Board b WHERE b.title LIKE %?1% ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest1(String searchKeyword);
	
	@Query("SELECT b FROM Board b ORDER BY b.seq DESC")
	List<Board> queryAnnotationTest2(Pageable paging);
	
}
