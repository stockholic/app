package com.taxholic.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxholic.batch.service.job2.PersonItemProcessor2;
import com.taxholic.batch.service.job2.PersonItemReader2;
import com.taxholic.batch.service.job2.PersonItemWriter2;
import com.taxholic.batch.vo.Person;


@Configuration
@EnableBatchProcessing
public  class TestJob2{
	
   @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Bean
    public Job job2() {
        return jobBuilderFactory.get("job2")
                .incrementer(new RunIdIncrementer())
                .flow(jobStep2())
                .end()
                .build();
    }

	@Bean
    public Step jobStep2() {
        return stepBuilderFactory.get("jobStep2")
                .<Person, Person> chunk(10)
                .reader(reader2())
                .processor(processor2())
                .writer(writer2())
                .build();
    }
    
    @Bean
    public PersonItemReader2 reader2() {
        return new PersonItemReader2();
    }

    @Bean
    public PersonItemProcessor2 processor2() {
        return new PersonItemProcessor2();
    }

    @Bean
    public PersonItemWriter2 writer2() {
    	return new PersonItemWriter2();
    }
}
