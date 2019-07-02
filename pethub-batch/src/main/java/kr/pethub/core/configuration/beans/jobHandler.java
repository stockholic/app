package kr.pethub.core.configuration.beans;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.pethub.job.crawling.service.PethubService;

@Component
public class jobHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PethubService crawlingService;
	
	/**
	 * job 분기처리
	 * @param params
	 */
	public void runJob(Map<String,String> params) {
		
		logger.info("Job name : {} : ",  params.get("job" ));
		
		String job  = params.get("job").toString();
		
		if("crawling".equals(job)){
			
			crawlingService.getId();
			
			logger.info("startDt : {} : ",  params.get("startDt" ));
			logger.info("endDt : {} : ",  params.get("endDt" ));
			
		}else {
			logger.info("Job is not found");
		}
		
		
	}
	
	
	
}
