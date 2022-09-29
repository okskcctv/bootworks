package com.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public Member getMember(Member member) {
		// findById의 반환형이 Optional<T>임
		// Optional은 주로 null 에러를 방지하기 위한 클래스임
		Optional<Member> findMember = memberRepo.findById(member.getId());
		if(findMember.isPresent()) {
			return findMember.get();
		}else {
			return null;
		}
	}

	@Override
	public List<Member> getMemberList() {
		List<Member> memberList = memberRepo.findAll();	// 모든 유저를 찾아서
		return memberList;	// 반환
	}

	@Override
	public void addMember(Member member) {
		member.setRole("ROLE_USER");
		memberRepo.save(member);	// 입력받은 맴버를 저장
	}

	@Override
	public Member viewMember(String id) {	// 맴버 조회
		return memberRepo.findById(id).get();
	}

	@Override
	public void updateMember(Member member) {
		Member findMember = memberRepo.findById(member.getId()).get();
		findMember.setName(member.getName());
		findMember.setPassword(member.getPassword());
		memberRepo.save(findMember);
	}

	@Override
	public void deleteMember(Member member) {	// 회원 탈퇴
		memberRepo.delete(member);
	}

}
