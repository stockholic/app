package kr.pethub.crawling;

import java.io.IOException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogZzangCoKr;

/**
 * 도그짱 http://www.dog-zzang.co.kr 
 * @author shkr
 *
 */
public class DogZzangCoKrTest extends BaseTestCase{
//public class DogZzangCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogZzangCoKr obj = new DogZzangCoKr();
	
	@Test
	public void 강아지_목록추출() throws IOException {
		
		String linkUrl = "http://www.dog-zzang.co.kr/dog_sale/safe_list.php";
		
		obj.getDogList(linkUrl);
	}
	
	@Test
	public void 강아지_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.dog-zzang.co.kr/dog_sale/sale_view.php?oid_no=zzang1562577722217&no=1118522&page=1&dog_kind=&area=&dog_money=");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		//siteSrl 일련번호
		service.crawling("4");
	}
}