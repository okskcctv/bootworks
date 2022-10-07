package com.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.boot.service.SecurityUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SecurityUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		// 사용자 정의 UserDetailsService 사용
		security.userDetailsService(userDetailsService);
		
		security.authorizeRequests()
				.antMatchers("/", "/member/**").permitAll()	// 인증되지 않은 모든 사용자 접근
				.antMatchers("/board/**").authenticated();	// 로그인한 사용자만 접근
		
		// security.csrf().disable();	// csrf 비활성화
		// security.formLogin();	// 스프링부트 제공 로그인 폼 실행
		security.formLogin().loginPage("/member/login")
				.defaultSuccessUrl("/board/getBoardList", true);
		
		security.logout().logoutUrl("/member/logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
