package kr.zchat.schedule.job.batch;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Component;

@Component
public class TestJob {
	
	@Scheduled(cron = "*/5 * * * * *")
	public void job() { 
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
	}

}
