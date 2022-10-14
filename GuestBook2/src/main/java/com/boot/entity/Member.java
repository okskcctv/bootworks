package com.boot.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity{
	
	@Id
	private String email;		// 이메일(기본키)
	private String password;	// 비밀번호
	private String name;		// 이름
}
