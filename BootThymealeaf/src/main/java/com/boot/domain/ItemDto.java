package com.boot.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ItemDto {
	
	private Long id;
	private String itemNm;
	private int price;
	private String itemDetail;
	private LocalDateTime regTime;
	
}
