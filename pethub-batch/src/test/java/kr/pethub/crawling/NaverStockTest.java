package kr.pethub.crawling;

import java.io.IOException;

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
	
	NaverStock obj = new NaverStock();
	
	@Test
	public void 목록추출() throws IOException{
		
		String linkUrl = "https://m.stock.naver.com/searchItem.nhn?searchType=init";
		
		obj.getList(linkUrl);
		
	}
	
	@Test
	public void 내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setContentLink("https://m.stock.naver.com/api/html/item/getOverallInfo.nhn?code=659432");
		
		obj.getContent(siteLinkData);
		
	}
	
	
	@Test
	public void 데이터저장(){
		service.crawling();
	}
}
