package com.boot.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Board;
import com.boot.entity.Reply;

@SpringBootTest
public class ReplyRepositoryTest {
	
	@Autowired
	ReplyRepository replyRepo;
	
	/*@Test
	public void insertReply() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Long bno = (long)(Math.random() * 100 + 1);
			Board board = Board.builder().bno(bno).build();
			
			Reply reply = Reply.builder()
					.rno((long)i)
					.text("댓글" + i)
					.replyer("user" + i)
					.board(board)
					.build();
			
			replyRepo.save(reply);
			
		});
	}*/
	
	// 댓글 조회
	@Transactional	// 지연 로딩 시 사용함
	@Test
	public void readReply() {
		Optional<Reply> result = replyRepo.findById(1L);
		
		Reply reply = result.get();
		
		System.out.println(reply);
		System.out.println(reply.getBoard());
	}
}
