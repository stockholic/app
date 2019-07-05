package kr.pethub.crawling;

import java.util.List;


import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.NaverStock;

public class NaverStockTest extends BaseTestCase{
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	@Test
	public void crawling(){
		
		NaverStock ns = new NaverStock();
		List<SiteLinkData> list =   ns.getList();
		
		for(int i = 0; i < list.size(); i++ ) {
			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (i + 1));

			logger.debug("ID : {}",  list.get(i).getDataId());
			logger.debug("TITLE : {}",  list.get(i).getDataTitle());
			logger.debug("LINK : {}",  list.get(i).getDataLink());
			logger.debug("IMAGE : {}",  list.get(i).getDataImg());
			
			SiteLinkData siteLinkData = new SiteLinkData();
			siteLinkData.setDataId(list.get(i).getDataId());
			siteLinkData.setContentLink(list.get(i).getContentLink());
			
			String content = ns.getContent(siteLinkData);
			logger.debug("CONTENT : {}",  content);
			
		}
		
	}
	
	
	@Test
	public void insertData(){
		
		service.crawling();
		
	}
}
