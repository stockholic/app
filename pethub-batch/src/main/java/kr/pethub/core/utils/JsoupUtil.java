package kr.pethub.core.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	public static Elements getElements(String url, String selector) throws IOException	 {
		return getElements( url, 5, "utf-8", selector);
	}

	public static Elements getElements(String url, String charSet, String selector) throws IOException	 {
		return getElements( url, 5, charSet, selector);
	}

	public static Elements getElements(String url, String timeout, String charSet, String selector) throws IOException	 {
		return getElements( url, timeout, charSet, selector);
	}

	public static Elements getElements(String url, int timeout, String charSet, String selector) throws IOException{
		
		String html = HttpClientUtil.getContent(url, timeout, charSet);
		
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(selector);
		
		return elements;
	 }
	
	
}
