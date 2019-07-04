package kr.pethub.site;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctc.wstx.sw.EncodingXmlWriter;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.vo.SiteLinkData;

public class NaverStock {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	public List<SiteLinkData> crawling() {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String url = "https://m.stock.naver.com/searchItem.nhn?searchType=init";
		String selector = "#searchResult li";
		
		String patternId ="(.*)(code=)([0-9]+)";
		

		try {
			
			Elements elements = JsoupUtil.getElements(url, selector);
			
			for( Element ele :  elements) {
				SiteLinkData cli  = new SiteLinkData();
				
				cli.setDataTitle( ele.getElementsByClass("stock_item").text() );		//제목
				
				String link = ele.getElementsByTag("a").attr("href").trim();	//링크
				cli.setDataLink( link );															
				
				//아이디 추출
				String id = link.replaceAll(patternId, "$3");
				cli.setDataId( id );
				
				//내용 추출	
				Elements contents = JsoupUtil.getElements("https://m.stock.naver.com/api/html/item/getOverallInfo.nhn?code=" + id, ".total_lst" );
				
				cli.setDataContent( contents.text() );
				
				list.add(cli);
			}
	
	
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}

		return list;
	}
	
	
	
}
