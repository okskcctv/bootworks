package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.Board;
import com.boot.repository.search.SearchBoardRepository;

public interface BoardRepository extends JpaRepository<Board, Long>,
SearchBoardRepository{
	
}
