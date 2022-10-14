package com.boot.repository;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.boot.entity.Board;
import com.boot.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired
	BoardRepository boardRepo;
	
	/*@Test
	public void insertBoard() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			Member member = Member.builder().email("user" + i + "@naver.com").build();
			Board board = Board.builder()
					.bno((long)i)
					.title("제목" + i)
					.content("내용" + i)
					.writer(member)
					.build();
			
			boardRepo.save(board);
			
		});
	}*/
	
	// 게시긇 작성자 조회 기능
	/*@Test
	public void testReadWithWriter() {
		Object result = boardRepo.getBoardWithWriter(100L);
		
		// 회원은 여러개의 글을 작성
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	// 게시글, 작성자, 댓글 수 조회
	/*@Test
	public void testWithReplyCount() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepo.getBoardWithReplyCount(pageable);
		
		result.get().forEach(row -> {
			
			Object[] arr = (Object[])row;
			System.out.println(Arrays.toString(arr));
		});
		
	}*/
	
	// 특정 게시물 조회
	/*@Test
	public void testRead() {
		Object result = boardRepo.getBoardByBno(100L);
		Object[] arr = (Object[]) result;
		
		System.out.println(Arrays.toString(arr));
	}*/
	
	@Test
	public void testSearch1() {
		boardRepo.search1();
	}
	
	@Test
	public void testSearchPage() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
		Page<Object[]> result = boardRepo.searchPage("twc", "1", pageable);
	}
	
}
