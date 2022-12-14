package com.boot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.boot.domain.Member;
import com.boot.domain.Role;
import com.boot.persistence.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private PasswordEncoder pwencoder;
	
	@Override
	public void signup(Member member) {
		// 비밀번호 암호화
		String encPw = pwencoder.encode(member.getPassword());
		member.setPassword(encPw);
		member.setRole(Role.ROLE_MEMBER);
		member.setEnabled(true);
		memberRepo.save(member);
	}

	@Override
	public void update(Member member) {
		memberRepo.save(member);
	}

	@Override
	public void delete(Member member) {
		memberRepo.delete(member);
	}

}
