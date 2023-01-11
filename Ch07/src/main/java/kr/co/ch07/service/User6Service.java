package kr.co.ch07.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ch07.repository.User6Repo;
import kr.co.ch07.vo.User6VO;

@Service
public class User6Service {

	@Autowired
	private User6Repo repo;
	
	public void insertuser6(User6VO vo) {
		repo.save(vo);
	}
	public User6VO selectuser6(String uid) {
		return repo.findById(uid).get();
	}
	public List<User6VO> selectuser6s(){
		return repo.findAll();
	}
	public void updateuser6(User6VO vo) {
		repo.save(vo);
	}
	public void deleteuser6(String uid) {
		repo.deleteById(uid);
	}
}
