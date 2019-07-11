package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.ChdogCoKr;

/**
 * 착한애견분양 http://chdog.co.kr
 * @author shkr
 *
 */
public class ChdogCoKrTest extends BaseTestCase{
//public class ChdogCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	ChdogCoKr obj = new ChdogCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException{
		
		String linkUrl = "http://chdog.co.kr/goods/goods_list.php?cateCd=001&pageNum=60";
		
		obj.getDogList(linkUrl);
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
