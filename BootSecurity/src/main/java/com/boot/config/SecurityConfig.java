package com.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// 시큐리티 설정 파일을 의미하며 시큐리티를 사용하는데 필요한 수많은 객체를 생성함
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity security) throws Exception{
		
		// AuthorizedUrl은 빌더 패턴을 사용
		security.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/member/**").authenticated()
				.antMatchers("/manager/**").hasRole("MANAGER")
				.antMatchers("/admin/**").hasRole("ADMIN");
		
		// security.formLogin();	// 시큐리티 기본 로그인 폼 사용
		
		security.csrf().disable(); // csrf() 비활성화 함
		
		// 사용자가 만든 로그인 폼이 실행되고, 결과 페이지로 이동
		security.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		
		// 접근 권한 없음 페이지 처리
		security.exceptionHandling().accessDeniedPage("/accessDenied");
		
		// 로그아웃 후 로그인 페이지로 이동
		security.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
		// 데이터베이스에 저장된 사용자로 인증 처리
		// query1 : 사용자가 입력한 아이디로 사용자 정보 조회
		// 아이디는 username에 비밀번호는 password 변수에 각각 저장함(Alias[별칭]를 적용)
		String query1 = "select id username, concat('{noop}', password) "
				+ "password, true enabled from member where id=?";
		// query2 : 사용자가 입력한 아이디로 권한 정보 조회
		String query2 = "select id, role from member where id=?";
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(query1)
			.authoritiesByUsernameQuery(query2);
			
		/* auth.inMemoryAuthentication()
			.withUser("manager")			// 사용자 아이디 설정
			.password("{noop}manager123")	// 비밀번호에 대한 암호화 하지 않음
			.roles("MANAGER");				// 권한 설정
		
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password("{noop}admin123")
			.roles("ADMIN"); */
	}
	
}
