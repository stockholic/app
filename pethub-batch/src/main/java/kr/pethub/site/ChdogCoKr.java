package kr.pethub.site;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.pethub.core.utils.JsoupUtil;
import kr.pethub.job.crawler.vo.SiteLinkData;

/**
 * 착한애견분양 http://chdog.co.kr
 * @author shkr
 *
 */
public class ChdogCoKr {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	/**
	 * 강이지 목록 추출
	 * @return
	 * @throws IOException 
	 */
	
	public List<SiteLinkData> getDogList(String linkUrl) throws IOException {
		
		List<SiteLinkData> list = new ArrayList<SiteLinkData>();
		
		String domain = "http://chdog.co.kr";
		String selector = "#content .list li";
		String patternId ="(.*)(goodsNo=)([0-9]+)";

		Elements elements = JsoupUtil.getElements(linkUrl, selector);
		
		int k = 1;
		for( Element ele :  elements) {
			
			logger.debug("--------------------------------------------------------------------------------------------------------------- " + (k++));
			
			SiteLinkData cli  = new SiteLinkData();
			
			//제목 추출
			String dataTitle = ele.getElementsByClass("txt").text();
			logger.debug( "TITEL : {}" , JsoupUtil.specialCharacterRemove(dataTitle));
			cli.setDataTitle( JsoupUtil.specialCharacterRemove(dataTitle));

			//링크 추출
			String dataLink = ele.select("a").attr("href").replace("..", ""); 
			logger.debug( "LINK : {}" , domain + dataLink );
			cli.setDataLink(domain + dataLink);

			//이미지 추출
			String dataImg = ele.getElementsByTag("img").attr("src");
			logger.debug( "IMAGE : {}" , domain + dataImg );
			cli.setDataImg(domain + dataImg);	
			
			//아이디 추출
			String dataId = dataLink.replaceAll(patternId, "$3");
			logger.debug( "ID : {}" , dataId );
			cli.setDataId( dataId );
			
			//내용 접근 URL
			cli.setDataLink(cli.getDataLink());	
			
			list.add(cli);
			
		}


		return list;
	}
	
	/**
	 * 강아지 내용 추출
	 * @return
	 * @throws IOException 
	 */
	public String getDogContent( SiteLinkData siteLinkData ) throws IOException {

		String selector = "body";
		Elements contents = JsoupUtil.getElements(siteLinkData.getDataLink() , selector );
		
		logger.debug( "CONTENTS : {}" ,  JsoupUtil.specialCharacterRemove(contents.text() ));
		
		return JsoupUtil.specialCharacterRemove( contents.text() );
	}
	
	
}