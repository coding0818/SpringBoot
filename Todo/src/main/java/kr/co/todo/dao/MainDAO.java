package kr.co.todo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import kr.co.todo.vo.MainVO;

@Mapper
@Repository
public interface MainDAO {

	public int insertContent(MainVO vo);
	public List<MainVO> selectReady();
	public List<MainVO> selectDone();
	public List<MainVO> selectDoing();
	public int updateContent(@Param("itemNo") int itemNo, @Param("status") int status);
	public int deleteContent(@Param("itemNo") int itemNo,@Param("status") int status);
	
}
