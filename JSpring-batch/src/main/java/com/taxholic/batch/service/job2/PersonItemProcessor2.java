package com.taxholic.batch.service.job2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taxholic.batch.core.AbstractItemProcessor;
import com.taxholic.batch.vo.Person;

public class PersonItemProcessor2 extends AbstractItemProcessor<Person, Person> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemProcessor2.class);

	@Override
	protected Person doProcess(Person obj) throws Exception {
		
		obj.setUser(obj.getUser() +" 2222");
		return obj;
		
	}

}
