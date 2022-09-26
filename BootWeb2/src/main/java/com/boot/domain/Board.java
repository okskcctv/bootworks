package com.boot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	//@Column(updatable=false)
	//private String writer;		// 글쓴이
	private String content;		// 내용
	
	@Column(insertable=false, updatable=false,
			columnDefinition = "timestamp default current_timestamp")
	private Date createDate;	// 작성 날짜
	
	@Column(insertable=false, updatable=false,
			columnDefinition = "bigint default 0")
	private Long cnt;			// 조회수
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
}
