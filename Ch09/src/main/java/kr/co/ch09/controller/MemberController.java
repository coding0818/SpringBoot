package kr.co.ch09.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ch09.service.MemberService;
import kr.co.ch09.vo.MemberVO;

@RestController
public class MemberController {

	@Autowired
	private MemberService service;
	
	@GetMapping("/member")
	public List<MemberVO> list1() {
		return service.selectMembers();
	}
	
	@GetMapping("/member/{id}")
	public MemberVO list2(@PathVariable("id") String uid) {
		return service.selectMember(uid);
	}
	
	@PostMapping("/member")
	public void register(MemberVO vo) {
		service.insertMember(vo);
	
	}
	
	@PutMapping("/member")
	public void modify(MemberVO vo) {
		 service.updateMember(vo);
	}
	
	@DeleteMapping("/member/{id}")
	public void delete(@PathVariable("id") String uid) {
		service.deleteMember(uid);
		
	}
}
