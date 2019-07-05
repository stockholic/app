package kr.pethub.job.crawler.service;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.job.crawler.dao.CrawlingDao;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.job.crawler.vo.SiteLink;

@Service
public class CrawlingService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlingDao crawlingDao;
	
	/**
	 * Reflction 을 이용하여 Class 호출 실행
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	public void crawling() {
		
		//사이트 Crawling 대상 URL
		List<SiteLink> linkList = crawlingDao.selectSiteLinkList();
		
		for( SiteLink lst : linkList ) {
			
			
			logger.info(lst.getLinkNm());
			
			try {
				
				Class<?> clasz = Class.forName(lst.getLinkClass());
				Object obj = clasz.newInstance();
				
				Method getList = clasz.getMethod(lst.getLinkMethod());
				List<SiteLinkData> list =  (List<SiteLinkData>)getList.invoke(obj);
				
				//추출한 데이터 저장
				for(int i = 0; i < list.size(); i++ ) {
					
					SiteLinkData siteLinkData = new SiteLinkData();
					siteLinkData.setSiteSrl(lst.getSiteSrl());
					siteLinkData.setLinkSrl(lst.getLinkSrl());
					siteLinkData.setDataId(list.get(i).getDataId());
					siteLinkData.setDataTitle(list.get(i).getDataTitle());
					siteLinkData.setDataLink(list.get(i).getDataLink());
					siteLinkData.setDataImg(list.get(i).getDataImg());
					
					siteLinkData.setContentLink(list.get(i).getContentLink());
					
					//update 후 없으면 insert
					if(crawlingDao.updateSiteLinkData(siteLinkData) == 0){
						
						Method getContent = clasz.getMethod("getContent", SiteLinkData.class);
						String content = (String)getContent.invoke(obj, siteLinkData);
						
						logger.debug("CONTENT : {}",  content);
						siteLinkData.setDataContent(content);
						
						crawlingDao.insertSiteLinkData(siteLinkData);
					}
					
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
}
