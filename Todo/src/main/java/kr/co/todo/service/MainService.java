package kr.co.todo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.todo.dao.MainDAO;
import kr.co.todo.vo.MainVO;

@Service
public class MainService {

	@Autowired
	private MainDAO dao;
	
	@Transactional
	public Map<Integer, List<MainVO>> selectAll() {
		List<MainVO> ready = dao.selectReady();
		List<MainVO> doing = dao.selectDoing();
		List<MainVO> done = dao.selectDone();
		
		Map<Integer, List<MainVO>> map = new HashMap<>();
		map.put(1, ready);
		map.put(2, doing);
		map.put(3, done);
		
		return map;
	}
	
	public int insertContent(MainVO vo) {
		return dao.insertContent(vo);
	}
	
	public int updateContent(int itemNo, int status) {
		return dao.updateContent(itemNo, status);
	}
	
	public int deleteContent(int itemNo, int status) {
		return dao.deleteContent(itemNo, status);
	}
	
}
