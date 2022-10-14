package com.boot.service;

import java.util.List;

import com.boot.dto.ReplyDTO;
import com.boot.entity.Board;
import com.boot.entity.Reply;

public interface ReplyService {
	
	// 댓글 목록 보기
	List<ReplyDTO> getList(Long bno);
	
	// 댓글 등록
	Long register(ReplyDTO replyDTO);
	
	// Reply 객체(엔티티)를 ReplyDTO로 반환
	default ReplyDTO entityToDTO(Reply reply) {
		ReplyDTO dto = ReplyDTO.builder()
						.rno(reply.getRno())
						.text(reply.getText())
						.replyer(reply.getReplyer())
						.regDate(reply.getRegDate())
						.modDate(reply.getModDate())
						.build();
		return dto;
	}
	
	// ReplyDTO를 Reply 객체로 반환
	default Reply DTOToEntity(ReplyDTO replyDTO) {
		// 게시글 1개 생성
		Board board = Board.builder().bno(replyDTO.getBno()).build();
		
		Reply reply = Reply.builder()
						.rno(replyDTO.getRno())
						.text(replyDTO.getText())
						.replyer(replyDTO.getReplyer())
						.board(board)
						.build();
		return reply;
	}
}
