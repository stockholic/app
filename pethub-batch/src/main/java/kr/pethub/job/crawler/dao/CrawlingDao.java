package kr.pethub.job.crawler.dao;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.MultiSqlSessionDaoSupport;

@Repository
public class CrawlingDao extends MultiSqlSessionDaoSupport{
	
	public int getCount() {
		return getInt("selectUserCount");
	}
	
	
}
