package com.boot.service;

import com.boot.domain.Member;

public interface MemberService {
	
	void signup(Member member);	// 회원 가입
	
	void update(Member member);	// 회원 수정
	
	void delete(Member member);	// 회원 삭제
}
