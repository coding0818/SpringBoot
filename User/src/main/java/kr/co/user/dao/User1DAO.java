package kr.co.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.user.vo.User1VO;

@Mapper
@Repository
public class User1DAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertUser1(User1VO vo) {
		mybatis.insert("insertUser1", vo);
	}
	public User1VO selectUser1(String uid) {
		return mybatis.selectOne("selectUser1", uid);
	}
	public List<User1VO> selectUser1s() {
		return mybatis.selectList("selectUser1s");
	}
	public void updateUser1(User1VO vo) {
		mybatis.update("udpateUser1", vo);
	}
	public void deleteUser1(String uid) {
		mybatis.delete("deleteUser1", uid);
	}
}
