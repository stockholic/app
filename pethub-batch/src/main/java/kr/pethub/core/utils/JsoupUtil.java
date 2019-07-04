package kr.pethub.core.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	public static Elements getElements(String url, String selector)	 {
		return getElements( url, 5000,  selector);
	}
	
	public static Elements getElements(String url, int timeout, String selector){
		
		Elements elements = null;
		try {
			
			Document doc = Jsoup.connect(url).timeout(timeout).get();
			elements = doc.select(selector);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return elements;
	 }
	
	
}
