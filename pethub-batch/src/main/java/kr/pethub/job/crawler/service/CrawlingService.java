package kr.pethub.job.crawler.service;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pethub.job.crawler.dao.CrawlingDao;
import kr.pethub.job.crawler.vo.SiteData;

@Service
public class CrawlingService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CrawlingDao crawlingDao;
	
	@SuppressWarnings("unchecked")
	public void crawling() {
		
		//crawlingDao.getCount();
		
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
		
		
		
	}
	
}
