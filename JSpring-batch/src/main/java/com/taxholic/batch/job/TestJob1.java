package com.taxholic.batch.job;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.taxholic.batch.service.job1.PersonItemProcessor;
import com.taxholic.batch.service.job1.PersonItemReader;
import com.taxholic.batch.service.job1.PersonItemWriter;
import com.taxholic.batch.vo.Person;


@Configuration
@EnableBatchProcessing
public  class TestJob1{
	
   @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;
    
    @Bean
    public Job job1() {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .flow(jobStep1())
                .end()
                .build();
    }

	@Bean
    public Step jobStep1() {
        return stepBuilderFactory.get("jobStep1")
                .<Person, Person> chunk(10)		//Processor 와 writer 에서 한번에 처리할 단위, 
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    
    @Bean
    @StepScope
    public PersonItemReader reader() {
        return new PersonItemReader();
    }

    @Bean
    @StepScope
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    @StepScope
    public PersonItemWriter writer() {
    	return new PersonItemWriter();
    }
}
