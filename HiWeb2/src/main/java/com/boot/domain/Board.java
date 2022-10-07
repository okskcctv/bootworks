package com.boot.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude="member")
@Getter
@Setter
@Entity
public class Board implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="BOARD_ID")
	private Long seq;
	
	private String title;
	
	private String content;
	
	@Column(updatable=false,
			columnDefinition = "timestamp DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime createDate;
	
	@Column(updatable=false,
			columnDefinition = "bigint DEFAULT 0")
	private Long cnt = 0L;
	
	// 다대일 연관 매핑
	@ManyToOne
	@JoinColumn(name="MEMBER_ID", nullable=false, updatable=false)
	private Member member;
	
	// 자동으로 MEMBER_ID 칼럼에 사용자 아이디가 설정되도록 함
	public void setMember(Member member) {
		this.member = member;
		member.getBoardList().add(this);
	}
}
