package kr.pethub.core.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class JsoupUtil {
	
	public static Elements getElements(String url, String selector) throws IOException	 {
		return getElements( url, 5000,  selector);
	}
	
	public static Elements getElements(String url, int timeout, String selector) throws IOException{
		
		Elements elements = null;
			
		Document doc = Jsoup.connect(url).timeout(timeout).get();
		elements = doc.select(selector);
		
		return elements;
	 }
	
	
}
