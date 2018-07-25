package com.taxholic.batch.service.job1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taxholic.batch.core.ItemListReader;
import com.taxholic.batch.dao.PersionDao;
import com.taxholic.batch.vo.Person;

public class PersonItemReader extends ItemListReader<Person> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemReader.class);
    
    @Autowired
	private PersionDao dao;
    
    @Value("#{jobParameters[startDt]}")
    private String startDt;

    @Value("#{jobParameters[endDt]}")
    private String endDt;
    
	@Override
	public List<Person> setList() {
		
		logger.debug("startDt : {} , endDt : {} ", startDt, endDt);

		List<Person> list =dao.getList();
		
		return list;
	}

}
