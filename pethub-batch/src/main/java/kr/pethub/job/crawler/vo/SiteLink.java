package kr.pethub.job.crawler.vo;

public class SiteLink {
	
	private String siteNm;
	private String linkNm;
	private String linkUrl;
	private String linkClass;
	private String linkMethod;
	
	public String getSiteNm() {
		return siteNm;
	}
	public void setSiteNm(String siteNm) {
		this.siteNm = siteNm;
	}
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getLinkClass() {
		return linkClass;
	}
	public void setLinkClass(String linkClass) {
		this.linkClass = linkClass;
	}
	public String getLinkMethod() {
		return linkMethod;
	}
	public void setLinkMethod(String linkMethod) {
		this.linkMethod = linkMethod;
	}
}