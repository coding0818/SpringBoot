package kr.co.sboard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.sboard.dao.UserDAO;
import kr.co.sboard.repository.UserRepo;
import kr.co.sboard.service.UserService;
import kr.co.sboard.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

class SboardApplicationTests {

	
	void contextLoads() {
	}

	@Autowired
	private UserDAO dao;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepo repo;
	
	public void insertTest() {
		
		UserVO vo = UserVO.builder()
					.uid("test102")
					.pass1("test102")
					.name("박가미")
					.nick("가미")
					.email("test101@gmail.com")
					.hp("010-4646-0910")
					.regip("0:0:0:0:0:0:0:1")
					.build();
		
		int result = service.insertUser(vo);
		
	}
	
	@Test
	public void countUid() {
		
		int result = repo.countUserEntityByUid("test101");
	}
}
