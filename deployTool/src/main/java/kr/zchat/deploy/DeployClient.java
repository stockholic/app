package kr.zchat.deploy;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.commons.configuration.ConfigurationException;


/**
 * Jenkins deploy tool
 * 
 * 대상 war maven 옵션
 * clean install -DskipTests=true  -Preal package
 *  
 * 
 *  톰켓 실행에 권한 문제가 있어 아래와 같이 스크립트에 sudo 권한 부여해주어야  함
 * 
 *  # visudo
 * 
 * merong  ALL=(ALL)       NOPASSWD:/etc/rc.d/init.d/tomcatd
 *
 */
public class DeployClient {
	
	 public static void main(String[] args) throws IOException, URISyntaxException, ConfigurationException {
		 DeployClientHandler handler = new DeployClientHandler();
		 handler.cmd(args);
	 }
         
}
