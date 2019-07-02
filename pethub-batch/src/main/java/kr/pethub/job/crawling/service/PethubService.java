package kr.pethub.job.crawling.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import kr.pethub.job.crawling.dao.PethubDao;

@Service
public class PethubService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PethubDao crawlingDao;
	
	private @Value("@{deploy}") String deploy;
			
	public void getId() {
		crawlingDao.getCount();
	}
	
}
