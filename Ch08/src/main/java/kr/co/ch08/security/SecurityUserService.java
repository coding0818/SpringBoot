package kr.co.ch08.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.ch08.repository.User2Repo;
import kr.co.ch08.vo.User2VO;

@Service
public class SecurityUserService implements UserDetailsService{

	@Autowired
	private User2Repo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 스프링 시큐리티 인증 동작방식은 아이디/패스워드를 한 번에 조회하는 방식이 아닌
		// 아이디만 이용해서 사용자 정보를 로딩하고 나중에 패스워드를 검증하는 방식
		User2VO user = repo.findById(username).get();
		
		if(user == null) {
			// 계정이 없는 경우
			throw new UsernameNotFoundException(username);
		}
		
		
		/*
		// security가 제공하는 기본 사용자 객체생성
		UserDetails userDts = User.builder()
								.username(user.getUid())
								.password(user.getPass())
								.roles("MEMBER")
								.build();
		*/
		
		// security가 세션에 등록하는 객체(빌드 패턴, 빌드 초기화)
		UserDetails myUser = MyUserDetails.builder()
								.uid(user.getUid())
								.pass(user.getPass())
								.name(user.getName())
								.grade(user.getGrade())
								.hp(user.getHp())
								.age(user.getAge())
								.rdate(user.getRdate())
								.build();
		
		return myUser;
	}

}
