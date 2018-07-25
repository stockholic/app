package com.taxholic.batch.service.job2;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.taxholic.batch.core.ItemListReader;
import com.taxholic.batch.dao.PersionDao;
import com.taxholic.batch.vo.Person;

public class PersonItemReader2 extends ItemListReader<Person> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemReader2.class);
    
    @Autowired
	private PersionDao dao;
    

	@Override
	public List<Person> setList() {
		
		List<Person> list =dao.getList();
		
		return list;
	}

}
