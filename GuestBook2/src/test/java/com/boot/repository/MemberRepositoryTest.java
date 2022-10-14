package com.boot.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.entity.Member;

@SpringBootTest
public class MemberRepositoryTest {
	
	@Autowired
	MemberRepository memberRepo;
	
	@Test
	public void insertMember() {
		IntStream.rangeClosed(1, 100).forEach(i -> {
			
			Member member = Member.builder()
					.email("user" + i + "@naver.com")
					.password("user")
					.name("user" + i)
					.build();
			
			memberRepo.save(member);
			
		});
	}
}
