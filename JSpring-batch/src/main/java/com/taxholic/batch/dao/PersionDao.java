package com.taxholic.batch.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taxholic.batch.vo.Person;

@Repository
public class PersionDao {	
	
	@Autowired
	 private SqlSession sqlSession;
	
	public List<Person> getList() {		
		return sqlSession.selectList("service.test.getUserInfo");
	}
	
}
