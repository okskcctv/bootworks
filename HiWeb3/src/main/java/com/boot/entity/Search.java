package com.boot.entity;

import lombok.Data;

@Data
public class Search {
	
	private String searchCondition;	// 검색 조건
	
	private String searchKeyword; // 검색어
}
