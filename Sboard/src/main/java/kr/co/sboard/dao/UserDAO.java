package kr.co.sboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import kr.co.sboard.vo.TermsVO;
import kr.co.sboard.vo.UserVO;

@Mapper
@Repository
public interface UserDAO {

	// 약관
	public TermsVO selectTerms();
	
	// 회원
	public int insertUser(UserVO vo);
	public UserVO selectUser(String uid, String pass);
	public List<UserVO> selectUsers();
	public int updateUser(UserVO vo);
	public int deleteUser(String uid, String pass);
}
