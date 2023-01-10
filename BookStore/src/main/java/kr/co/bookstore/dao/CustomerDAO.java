package kr.co.bookstore.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.bookstore.vo.CustomerVO;

@Mapper
@Repository
public class CustomerDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	public void insertCustomer(CustomerVO vo) {
		mybatis.insert("insertCustomer", vo);
	}
	public CustomerVO selectCustomer(int custId) {
		return mybatis.selectOne("selectCustomer", custId);
	}
	public List<CustomerVO> selectCustomers() {
		return mybatis.selectList("selectCustomers");
	}
	public void updateCustomer(CustomerVO vo) {
		mybatis.update("updateCustomer", vo);
	}
	public void deleteCustomer(int custId) {
		mybatis.delete("deleteCustomer", custId);
	}
}
