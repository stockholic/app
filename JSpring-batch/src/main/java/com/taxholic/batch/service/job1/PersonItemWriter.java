package com.taxholic.batch.service.job1;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taxholic.batch.core.AbstractItemWriter;
import com.taxholic.batch.vo.Person;

public class PersonItemWriter extends AbstractItemWriter<Person> {

    private static final Logger logger = LoggerFactory.getLogger(PersonItemWriter.class);

	@Override
	protected void doWrite(List<? extends Person> list) throws Exception {
		
		
		 Stream<? extends Person> stream = list.stream();
//	        stream.forEach(s -> {
//	            String user = s.getUser();
//	            String host = s.getHost();
//	            System.out.println(user + " - " + host);
//		});
	        
        stream.forEach(s -> System.out.println(s.getUser()  +" : "+ s.getHost() ));
	}


}
