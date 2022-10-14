package com.boot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.boot.dto.ReplyDTO;
import com.boot.entity.Board;
import com.boot.entity.Reply;
import com.boot.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{
	
	private final ReplyRepository replyRepo;
	
	// 댓글 목록
	@Override
	public List<ReplyDTO> getList(Long bno) {
		
		Board board = Board.builder().bno(bno).build();
		
		List<Reply> result = replyRepo.getRepliesByBoardOrderByRno(board);
		
		return result.stream().map(reply -> entityToDTO(reply))
						.collect(Collectors.toList());
	}
	
	// 댓글 쓰기
	@Override
	public Long register(ReplyDTO replyDTO) {
		Reply reply = DTOToEntity(replyDTO);
		replyRepo.save(reply);
		return reply.getRno();
	}
	
	
}
