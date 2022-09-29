package com.boot.service;

import java.util.List;

import com.boot.domain.Member;

public interface MemberService {
	
	Member getMember(Member member);	// 로그인
	
	List<Member> getMemberList();	// 회원 목록
	
	void addMember(Member member);	// 회원가입
	
	Member viewMember(String id);	// 회원정보
	
	void updateMember(Member member);	// 회원 수정
	
	void deleteMember(Member member);	// 회원 탈퇴
}
