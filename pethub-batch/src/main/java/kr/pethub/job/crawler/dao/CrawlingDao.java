package kr.pethub.job.crawler.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.pethub.core.module.MultiSqlSessionDaoSupport;
import kr.pethub.job.crawler.vo.SiteLink;
import kr.pethub.job.crawler.vo.SiteLinkData;

@Repository
public class CrawlingDao extends MultiSqlSessionDaoSupport{
	
	/**
	 * 사이트 URL 목록
	 * @return
	 */
	public List<SiteLink> selectSiteLinkList() {
		return selectList("selectSiteLinkList");
	}
	
	/**
	 * 사이트 데이타 조회
	 * @param siteLinkData
	 * @return
	 */
	public List<SiteLinkData> selectSiteLinkDataList(SiteLinkData siteLinkData) {
		return selectList("selectSiteLinkDataList", siteLinkData);
	}
	
	/**
	 * 사이트 데이타 등록
	 * @param siteLinkData
	 * @return
	 */
	public int insertSiteLinkData(SiteLinkData siteLinkData) {
		return insert("insertSiteLinkData", siteLinkData);
	}
	
	/**
	 * 사이트 데이타 수정
	 * @param siteLinkData
	 * @return
	 */
	public int updateSiteLinkData(SiteLinkData siteLinkData) {
		return update("updateSiteLinkData", siteLinkData);
	}
	
	/**
	 * 사이트 데이타 삭제
	 * @param dataSrl
	 * @return
	 */
	public int deleteSiteLinkData(Integer dataSrl) {
		return delete("deleteSiteLinkData", dataSrl);
	}
	
}
