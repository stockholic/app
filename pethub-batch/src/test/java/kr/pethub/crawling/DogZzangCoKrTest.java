package kr.pethub.crawling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import kr.pethub.job.crawler.service.CrawlingService;
import kr.pethub.job.crawler.vo.SiteLinkData;
import kr.pethub.site.DogZzangCoKr;

/**
 * 도그짱 http://www.dog-zzang.co.kr 
 * @author shkr
 *
 */
//public class DogZzangCoKr extends BaseTestCase{
public class DogZzangCoKrTest {
	
	 Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	@Autowired
	private CrawlingService  service;
	
	DogZzangCoKr obj = new DogZzangCoKr();
	
	@Test
	public void 강아지_목록추출() {
		
		

		String USER_AGENT = "Mozila/5.0";
	    String GET_URL = "http://www.dog-zzang.co.kr/dog_sale/safe_list.php"; 

	    try {
		
			//http client 생성
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	 
	        //get 메서드와 URL 설정
	        HttpGet httpGet = new HttpGet(GET_URL);
	 
	        //agent 정보 설정
	        httpGet.addHeader("User-Agent", USER_AGENT);
	        
	        //get 요청
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        
	        //response의 status 코드 출력
	        System.out.println(httpResponse.getStatusLine().getStatusCode());
	 
	//        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	        BufferedReader reader = new BufferedReader( new InputStreamReader(httpResponse.getEntity().getContent(), "euc-kr") );
	 
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        
	        reader.close();
	 
	        //Print result
	        System.out.println(response.toString());
	        
	        
	        httpClient.close();
	        
	    }catch(Exception e) {
	    	e.getStackTrace();
	    }

		
		
//		
//		String linkUrl = "http://www.dog-zzang.co.kr/dog_sale/safe_list.php";
//		
//		obj.getDogList(linkUrl);
	}
	
	//에러남
	@Test
	public void 강아지_내용추출() throws IOException{
		
		
		String USER_AGENT = "Mozila/5.0";
	    String GET_URL = "http://www.zooseyo.com/sale/sale_view.php?type=f&oid_no=bbag1562393946424&no=277606&page=1&kind=&area="; 

	    try {
		
			//http client 생성
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	 
	        //get 메서드와 URL 설정
	        HttpGet httpGet = new HttpGet(GET_URL);
	 
	        //agent 정보 설정
	        httpGet.addHeader("User-Agent", USER_AGENT);
	        
	        //get 요청
	        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
	        
	        //response의 status 코드 출력
	        System.out.println(httpResponse.getStatusLine().getStatusCode());
	 
	        BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
	        //BufferedReader reader = new BufferedReader( new InputStreamReader(httpResponse.getEntity().getContent(), "euc-kr") );
	 
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	 
	        while ((inputLine = reader.readLine()) != null) {
	            response.append(inputLine);
	        }
	        
	        reader.close();
	 
	        //Print result
	        System.out.println(response.toString());
	        
	        
	        httpClient.close();
	        
	    }catch(Exception e) {
	    	e.getStackTrace();
	    }

		
		
//		SiteLinkData siteLinkData = new SiteLinkData();
//		siteLinkData.setDataLink("http://www.zooseyo.com/sale/sale_view.php?type=f&oid_no=bbag1562393946424&no=277606&page=1&kind=&area=");
//		
//		obj.getDogContent(siteLinkData);
//		
	}
	
	@Test
	public void 고양이_목록추출() throws IOException{
		
		String linkUrl = "http://www.zooseyo.com/sale/sale_list.php?animal=cat";
		
		obj.getDogList(linkUrl);
	}
	
	//에러남
	//@Test
	public void 고양이_내용추출() throws IOException{
		
		SiteLinkData siteLinkData = new SiteLinkData();
		siteLinkData.setDataLink("http://www.zooseyo.com/sale/sale_view.php?type=f&oid_no=bbag1562326785187&no=277531&page=1&kind=&area=");
		
		obj.getDogContent(siteLinkData);
		
	}
	
	@Test
	public void 데이터저장(){
		//siteSrl 일련번호
		service.crawling("4");
	}
}
