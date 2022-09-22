package com.boot.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int seq;			// 번호
	private String title;		// 제목
	private String writer;		// 글쓴이
	private String content;		// 내용
	private Date createDate;	// 작성 날짜
	private int cnt;			// 조회수
	
}
