package kr.pethub.job.crawling.dao;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.MultiSqlSessionDaoSupport;

@Repository
public class PethubDao extends MultiSqlSessionDaoSupport{
	
	public int getCount() {
		return getInt("selectUserCount");
	}
	
	
}
