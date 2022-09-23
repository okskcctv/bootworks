package com.boot;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.domain.Board;
import com.boot.domain.Member;
import com.boot.persistence.BoardRepository;
import com.boot.persistence.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardRepositoryTest{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Test
	public void testDataInsert() {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setPassword("member111");
		member1.setName("뽀로로");
		member1.setRole("ROLE_USER");
		memberRepo.save(member1);
		
		Member member2 = new Member();
		member2.setId("member2");
		member2.setPassword("member222");
		member2.setName("아기상어");
		member2.setRole("ROLE_ADMIN");
		memberRepo.save(member2);
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("뽀로로");
			board.setTitle("뽀로로가 등록한 게시글 " + i);
			board.setContent("뽀로로가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
		
		for(int i=1; i<=3; i++) {
			Board board = new Board();
			board.setWriter("아기상어");
			board.setTitle("아기상어가 등록한 게시글 " + i);
			board.setContent("아기상어가 등록한 게시글 내용 " + i);
			boardRepo.save(board);
		}
	}
	
	/*@Test
	public void testInsertBoard() {
		Board board = new Board();
		board.setTitle("첫 번째 게시글");
		board.setWriter("테스터");
		board.setContent("등록이 잘 되네요...");
		board.setCreateDate(new Date());
		board.setCnt(0L);
		
		boardRepo.save(board);	// save() 메서드로 DB에 저장함
	}*/
	
	// 상세 조회
	/*@Test
	public void testGetBoard() {
		// 상세조회 - findById(기본키값)
		Board board = boardRepo.findById(1L).get();
		log.info(board.toString());
	}*/
	
	/*@Test
	public void testBoardAll() {
		List<Board> boardList = (List<Board>) boardRepo.findAll();
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	
	// 글 수정
	/*@Test
	public void testUpdateBoard() {
		log.info("1번 게시글 조회");
		// 1번 게시글 가져옴
		Board board = boardRepo.findById(1L).get();
		
		log.info("1번 게시글 제목 수정");
		board.setTitle("제목을 수정합니다.");
		// 수정후 저장
		boardRepo.save(board);
	}*/
	
	// 글 삭제
	/*@Test
	public void testDeleteBoard() {
		
		log.info("2번 게시글 삭제");
		
		boardRepo.deleteById(2L);
	}*/
}