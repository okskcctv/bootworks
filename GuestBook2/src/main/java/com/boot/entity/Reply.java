package com.boot.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString(exclude="board")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Reply extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;		// 댓글 번호
	private String text;	// 댓글
	private String replyer;	// 댓글 작성자
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Board board;
}
