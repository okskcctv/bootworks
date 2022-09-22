package com.boot.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Entity
public class Board {
	
	@Id@GeneratedValue
	private Long seq;			// 번호
	
	private String title;		// 제목
	private String writer;		// 글쓴이
	private String content;		// 내용
	
	private Date createDate;	// 작성 날짜
	private Long cnt;			// 조회수
	
}
