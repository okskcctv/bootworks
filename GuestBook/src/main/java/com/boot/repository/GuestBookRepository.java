package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.boot.entity.GuestBook;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long>, 
QuerydslPredicateExecutor<GuestBook>{
	
	
}
