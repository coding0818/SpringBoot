package kr.co.farmstory.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private SecurityUserService service;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		// 인가(접근권한) 설정
		http.authorizeHttpRequests().antMatchers("/").permitAll(); // 모든 자원에 대해서 모든 사용자 접근 허용
		http.authorizeHttpRequests().antMatchers("/board/write").hasAnyRole("3", "4", "5");
		http.authorizeHttpRequests().antMatchers("/board/list").hasAnyRole("3", "4", "5");

		// 사이트 위변조 요청 방지
		http.csrf().disable();
		
		/*
		// 자동 로그인 설정
		http.rememberMe()
			.key("autoUser")
			.rememberMeParameter("autoUid")
			.tokenValiditySeconds(600)
			.userDetailsService(service);
		*/	


		// 로그인 설정
		http.formLogin()
		.loginPage("/user/login")
		.defaultSuccessUrl("/index")
		.failureUrl("/user/login?success=100")
		.usernameParameter("uid")
		.passwordParameter("pass");
		
		// 로그아웃 설정
		http.logout()
		.invalidateHttpSession(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/user/login?success=200");

		// 사용자 인증 처리 컴포넌트 서비스 등록
		http.userDetailsService(service);
		
		return http.build();
	}
	
	@Bean
    public PasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }
}
