package com.boot.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Getter
@Setter
@Entity
public class Member extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String userid;
	
	private String password;
	
	private String name;
	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	private boolean enabled;
	
}
