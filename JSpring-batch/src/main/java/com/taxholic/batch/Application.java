package com.taxholic.batch;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
	
    public static void main(String[] args) throws Exception {
//       SpringApplication.run(Application.class, args);
    	
    	 SpringApplication app = new SpringApplication(Application.class);
         app.setWebEnvironment(false);
         ConfigurableApplicationContext ctx= app.run(args);
         JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
//         JobParameters jobParameters = new JobParametersBuilder()
//             .addDate("date", new Date())
//             .toJobParameters();
         
   
         
         if(args.length > 0 ){
//        	 jobLauncher.run(ctx.getBean(args[0],  Job.class), jobParameters);   
        	 
             Map<String, JobParameter> map = new HashMap<String, JobParameter>();
             map.put("date", new JobParameter(new Date()));
             for(int i = 1; i < args.length; i++){
            	 String[] params = args[i].split("=");
            	 map.put(params[0], new JobParameter((params.length > 1)?params[1]:""));
             }
        	 
             try {
            	 jobLauncher.run(ctx.getBean(args[0], Job.class), new JobParameters(map));   
             }catch(Exception e){
            	 help();
             }
        	 
         } else{
        	help();
         }
    
    	System.exit(0);

    }
    
    
    public static void help(){
	   	 System.out.println("-----------------------------------------------");
	   	 System.out.println("\t잡명을 입력하세요 !!");
	   	 System.out.println("\tUage : 잡명 [파라미터 ...]");
	   	 System.out.println("\tex) job start=2015 end=2016");
	   	 System.out.println("-----------------------------------------------");
    }
    
}
