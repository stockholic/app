package kr.pethub.crawling;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.BaseTestCase;
import kr.pethub.job.crawling.service.PethubService;


//public class Pethub extends BaseTestCase{
	public class Pethub {
	
	
	@Autowired
	private PethubService pethubService;
	
	@Test
	public void 데이터베이스_커넥션() {
		
		//logger.debug("-------------------------------------------------------------------------------> start");
		
		pethubService.getId();
		
		//logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	
	@Test
	public void JSOUP_기본() {

		try {
			
			Document doc = Jsoup.connect("https://m.stock.naver.com/searchItem.nhn?searchType=init").timeout(5000).get();
			Elements contents = doc.select("#searchResult li");
			
			for( Element ele :  contents) {
				System.out.println( ele.getElementsByClass("stock_item").text() +" :  "+ ele.getElementsByClass("stock_price").text());
			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
