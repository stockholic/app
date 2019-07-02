package kr.pethub.crawling;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawling.service.PethubService;


public class Pethub extends BaseTestCase{
	
	
	@Autowired
	private PethubService pethubService;
	
	@Test
	public void 데이터베이스_커넥션() {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		pethubService.getId();
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
}
