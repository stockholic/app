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

public class DogmaruCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 대상 목록 추출
	 * @return
	 */
	public List<SiteLinkData> getList() {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String url = "https://dogmaru.co.kr/sdog";
		String selector = "#post_card_b201807025b39d19969307 .ma-item";
		
		String prifix = "https://dogmaru.co.kr";
		String patternImg = "(background-image: url)\\(([^\\s]+)\\)(.*)";
		String patternId ="(.*)(idx=)([0-9]+)(.*)";

		Elements elements = JsoupUtil.getElements(url, selector);
		
		int k = 1;
		for( Element ele :  elements) {
			
			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
			
			SiteLinkData cli  = new SiteLinkData();
			
			//제목 추출
			String dataTitle = ele.select(".title span").text();
			logger.debug( "TITEL : {} " , dataTitle );
			cli.setDataTitle( dataTitle) ;
			
			//링크 추출
			String dataLink = ele.select("a").attr("href"); 
			logger.debug( "LINK : {} " , prifix + dataLink );
			cli.setDataLink(prifix + dataLink);
			
			//이미지 추출
			String dataImg = ele.select(".card_wrapper").attr("style");
			dataImg = dataImg.replaceAll(patternImg, "$2");
			logger.debug( "IMAGE : {} " , dataImg );
			cli.setDataImg(dataImg);;	
			
			//아이디 추출
			String dataId = dataLink.replaceAll(patternId, "$3");
			logger.debug( "ID : {} " , dataId );
			cli.setDataId( dataId );
			
			list.add(cli);
			
		}


		return list;
	}
	
	/**
	 * 내용 추출
	 * @return
	 */
	public String getContent( SiteLinkData siteLinkData ) {

		String selector = "#w201901175c3fe684e2c7c .tableStrong";
		Elements contents = JsoupUtil.getElements(siteLinkData.getContentLink() , selector );
		
		logger.debug( "CONTENTS : {} " , contents.text() );
		
		return contents.text();
	}
	
	
}
