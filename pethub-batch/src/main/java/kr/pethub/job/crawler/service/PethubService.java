package kr.pethub.job.crawler.service;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.pethub.job.crawler.dao.PethubDao;

@Service
public class PethubService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PethubDao crawlingDao;
	
	private @Value("@{deploy}") String deploy;
			
	public void crawling() {
		
		//crawlingDao.getCount();
		
		Object obj = null;
		
		try {
			
			Class<?> clasz = Class.forName("kr.pethub.site.Pethub");
			if(obj == null) obj = clasz.newInstance();
			
			Method method = clasz.getMethod("crawling1");
			method.invoke(obj);
			
		}catch(Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		
		
		
	}
	
}
