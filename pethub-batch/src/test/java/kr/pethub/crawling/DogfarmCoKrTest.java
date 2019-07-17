package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogfarmCoKr;

/**
 * 도그팜 http://www.dogfarm.co.kr/
 * @author shkr
 *
 */
//public class DogfarmCoKr extends BaseTestCase{
public class DogfarmCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogfarmCoKr obj = new DogfarmCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://www.dogfarm.co.kr/gnu/index.php";
		
		obj.getDogList1(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://chdog.co.kr/goods/goods_view.php?goodsNo=1000000688");
		
		//내용 이미지 , 안함
		//obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		service.crawling("6");
	}
}
