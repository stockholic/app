package kr.pethub.site;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.vo.SiteData;

public class NaverStock {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public List<SiteData> crawling() {
		
		List<SiteData> list = new ArrayList<SiteData>();
		
		String url = "https://m.stock.naver.com/searchItem.nhn?searchType=init";
		String selector = "#searchResult li";
		
		String patternId ="(.*)(code=)([0-9]+)";
		

		try {
			
			Elements elements = JsoupUtil.getElements(url, selector);
			
			for( Element ele :  elements) {
				SiteData cli  = new SiteData();
				
				cli.setTitle( ele.getElementsByClass("stock_item").text() );		//제목
				
				String link = ele.getElementsByTag("a").attr("href").trim();	//링크
				cli.setLink( link );															
				
				//아이디 추출
				String id = link.replaceAll(patternId, "$3");
				cli.setId( id );
				
				//내용 추출	
				Elements contents = JsoupUtil.getElements("https://m.stock.naver.com/api/html/item/getOverallInfo.nhn?code=" + id, ".total_lst" );
				
				cli.setContent( contents.text() );
				
				list.add(cli);
			}
	
	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
}
