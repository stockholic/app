package kr.pethub.crawling;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.job.crawler.vo.CrawlingInfo;
import kr.pethub.site.Pethub;

public class PethubTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void pethub() {
		
		Pethub tc = new Pethub();
		List<CrawlingInfo> list =   tc.crawling1();
		
		for(int i = 0; i < list.size(); i++ ) {
			logger.debug("------------------------------------------------------------------------------------------------------------------------------ " + (i + 1));

			logger.debug("ID : {}",  list.get(i).getId());
			logger.debug("TITLE : {}",  list.get(i).getTitle());
			logger.debug("LINK : {}",  list.get(i).getLink());
			logger.debug("IMG : {}",  list.get(i).getImg());
			logger.debug("CONTENT : {}",  list.get(i).getContent());
		}
		
	}
}
