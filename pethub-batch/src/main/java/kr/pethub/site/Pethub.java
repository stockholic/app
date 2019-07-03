package kr.pethub.site;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.job.crawler.vo.CrawlingInfo;

public class Pethub {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public List<CrawlingInfo> crawling1() {
		
		List<CrawlingInfo> list = new ArrayList<CrawlingInfo>();

		try {
			
			Document doc = Jsoup.connect("https://m.stock.naver.com/searchItem.nhn?searchType=init").timeout(5000).get();
			Elements contents = doc.select("#searchResult li");
			
			for( Element ele :  contents) {
				CrawlingInfo cli  = new CrawlingInfo();
				cli.setTitle(ele.getElementsByClass("stock_item").text() );		//제목
				list.add(cli);
			}
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
}
