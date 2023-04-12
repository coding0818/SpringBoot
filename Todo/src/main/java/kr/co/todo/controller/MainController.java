package kr.co.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.todo.service.MainService;
import kr.co.todo.vo.MainVO;

@Controller
public class MainController {

	@Autowired
	private MainService service;
	
	@GetMapping("index")
	public String index(Model model) {
		Map<Integer, List<MainVO>> result = service.selectAll();
		List<MainVO> ready = result.get(1);
		List<MainVO> doing = result.get(2);
		List<MainVO> done = result.get(3);
		
		model.addAttribute("ready", ready);
		model.addAttribute("doing", doing);
		model.addAttribute("done", done);
		return "index";
	}
	
	@ResponseBody
	@PostMapping("insert")
	public Map<String, Object> insert(MainVO vo) {
		vo.setStatus(1);
		int result = service.insertContent(vo);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("result", result);
		resultMap.put("vo", vo);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("delete")
	public Map<String, Integer> delete(int itemNo, int status) {
		int result = service.deleteContent(itemNo, status);
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
	
	@ResponseBody
	@GetMapping("update")
	public Map<String, Integer> update(int itemNo, int newstatus) {
		int result = service.updateContent(itemNo, newstatus);
		Map<String, Integer> resultMap = new HashMap<>();
		resultMap.put("result", result);
		return resultMap;
	}
}
