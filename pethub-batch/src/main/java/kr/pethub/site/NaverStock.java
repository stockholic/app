package kr.pethub.site;

import java.io.IOException;
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
	
	
	/**
	 * 대상 목록 추출
	 * @return
	 * @throws IOException 
	 */
	public List<SiteLinkData> getList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String selector = "#searchResult li";
		String patternId ="(.*)(code=)([0-9]+)";

		String contentUrl = "https://m.stock.naver.com/api/html/item/getOverallInfo.nhn?code=";
		
		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		
		for( Element ele :  elements) {
			SiteLinkData cli  = new SiteLinkData();
			
			String dataTitle = ele.getElementsByClass("stock_item").text();
			cli.setDataTitle( ele.getElementsByClass("stock_item").text() );		//제목
			logger.debug( "TITEL : {} " , dataTitle );
			
			String link = ele.getElementsByTag("a").attr("href").trim();			//링크
			cli.setDataLink( link );
			cli.setContentLink( contentUrl );
			
			//아이디 추출
			String dataId = link.replaceAll(patternId, "$3");
			cli.setDataId( dataId );
			
			list.add(cli);
		}


		return list;
	}
	
	/**
	 * 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public String getContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = ".total_lst";
		Elements contents = JsoupUtil.getElements(siteLinkData.getContentLink(), selector );
		
		logger.debug( "CONTENTS : {} " , contents.text() );
		
		return contents.text();
	}
	
	
}
