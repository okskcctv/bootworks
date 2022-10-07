package com.boot.Repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.boot.dto.GuestBookDto;
import com.boot.dto.PageRequestDto;
import com.boot.dto.PageResultDto;
import com.boot.entity.GuestBook;
import com.boot.repository.GuestBookRepository;
import com.boot.service.GuestBookService;

@SpringBootTest
public class GuestBookRepositoryTests {
	
	@Autowired
	private GuestBookRepository guestBookRepository;
	
	// 데이터 300개 입력
	/*@Test
	public void insertData() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			GuestBook guestBook = GuestBook.builder()
					.title("Title" + i)
					.content("Content" + i)
					.writer("user" + (i % 10))
					.build();
			
			System.out.println(guestBookRepository.save(guestBook));
		});
	}*/
	
	// 수정 테스트
	@Test
	public void updateTest() {
		Optional<GuestBook> result = guestBookRepository.findById(300L);
		
		if(result.isPresent()) {
			GuestBook guestBook = result.get();
			
			guestBook.changeTitle("제목 수정");
			guestBook.changeContent("내용 수정");
			
			guestBookRepository.save(guestBook);
		}
	}
	
}
