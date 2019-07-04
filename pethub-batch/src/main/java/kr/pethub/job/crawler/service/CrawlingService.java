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
	@SuppressWarnings("unchecked")
	public void crawling() {
		
		List<SiteLink> linkList = crawlingDao.selectSiteLinkList();
		
		for( SiteLink lst : linkList ) {
			
			
			logger.info(lst.getLinkNm());
			
			try {
				
				Class<?> clasz = Class.forName(lst.getLinkClass());
				Object obj = clasz.newInstance();
				
				Method method = clasz.getMethod(lst.getLinkMethod());
				List<SiteLinkData> list =  (List<SiteLinkData>) method.invoke(obj);
				
				//추출한 데이터 저장
				for(int i = 0; i < list.size(); i++ ) {
					logger.debug("--------------------------------------------------------------------------------------------------------------- " + (i + 1));

					logger.debug("ID : {}",  list.get(i).getDataId());
					logger.debug("TITLE : {}",  list.get(i).getDataTitle());
					logger.debug("LINK : {}",  list.get(i).getDataLink());
					logger.debug("IMG : {}",  list.get(i).getDataImg());
					logger.debug("CONTENT : {}",  list.get(i).getDataContent());
					
					SiteLinkData siteLinkData = new SiteLinkData();
					siteLinkData.setDataId(list.get(i).getDataId());
					siteLinkData.setDataTitle(list.get(i).getDataTitle());
					siteLinkData.setDataLink(list.get(i).getDataLink());
					siteLinkData.setDataImg(list.get(i).getDataImg());
					siteLinkData.setDataContent(list.get(i).getDataContent());
					
					//update 후 없으면 insert
					if(crawlingDao.updateSiteLinkData(siteLinkData) == 0){
						crawlingDao.insertSiteLinkData(siteLinkData);
					}
					
				}
				
			}catch(Exception e) {
				logger.error(e.toString());
				e.printStackTrace();
			}
			
			
		}
		
		/*
		Object obj = null;
		
		try {
			
			Class<?> clasz = Class.forName("kr.pethub.site.NaverStock");
			if(obj == null) obj = clasz.newInstance();
			
			Method method = clasz.getMethod("crawling");
			List<SiteData> list =  (List<SiteData>) method.invoke(obj);
			
			for(int i = 0; i < list.size(); i++ ) {
				logger.debug("--------------------------------------------------------------------------------------------------------------- " + (i + 1));

				logger.debug("ID : {}",  list.get(i).getId());
				logger.debug("TITLE : {}",  list.get(i).getTitle());
				logger.debug("LINK : {}",  list.get(i).getLink());
				logger.debug("IMG : {}",  list.get(i).getImg());
				logger.debug("CONTENT : {}",  list.get(i).getContent());
			}
			
		}catch(Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		*/
		
		
		
	}
	
}
