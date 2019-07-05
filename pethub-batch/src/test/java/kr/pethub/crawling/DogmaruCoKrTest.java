package kr.pethub.crawling;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogmaruCoKr;

public class DogmaruCoKrTest extends BaseTestCase{
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogmaruCoKr obj = new DogmaruCoKr();
	
	@Test
	public void 목록추출(){
		
		String linkUrl = "https://dogmaru.co.kr/sdog";
		
		obj.getList(linkUrl);
	}
	
	@Test
	public void 내용추출(){
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setContentLink("https://dogmaru.co.kr/sdog/?q=YToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9&bmode=view&idx=1659432&t=board");
		
		obj.getContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling();
	}
}
