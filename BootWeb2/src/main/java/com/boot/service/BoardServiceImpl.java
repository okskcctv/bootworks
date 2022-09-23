package com.boot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.boot.domain.Board;
import com.boot.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Override
	public List<Board> getBoardList() {	// 게시글 목록 보기
		// findAll() 함수 사용
		return boardRepo.findAll(Sort.by(Sort.Direction.DESC, "seq"));
	}

	@Override
	public void insertBoard(Board board) {	// 게시글 등록
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(long seq) {	// 게시글 상세 조회
		return boardRepo.findById(seq).get();
	}

	@Override
	public void updateBoard(Board board) {	// 게시글 수정
		Board findBoard = boardRepo.findById(board.getSeq()).get();
		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {	// 게시글 삭제
		boardRepo.delete(board);
	}

	@Transactional	// 트랜잭션 처리하는 어노테이션
	@Override
	public void updateCnt(Long seq) {	// 조회수 증가
		boardRepo.updateCnt(seq);
	}
	
}
