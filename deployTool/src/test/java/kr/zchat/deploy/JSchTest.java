package kr.zchat.deploy;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.zchat.deploy.util.JSchUtil;
import kr.zchat.deploy.util.SysUtil;

public class JSchTest {

	Logger logger = LoggerFactory.getLogger(getClass());

	private Configuration prop;

	@Test
	public void Test_LS_AL() throws ConfigurationException, URISyntaxException {

		logger.debug("-------------------------------------------------------------------------------> start");

		this.prop = new PropertiesConfiguration("server.properties");
		
		
		String[] host = prop.getStringArray("server.host");
		String[] port = prop.getStringArray("server.port");
		String[] user = prop.getStringArray("server.user");
		String[] password = prop.getStringArray("server.password");
		
		for( int i = 0; i < host.length; i++ ) {
			System.out.println(host[i]);
			
			JSchUtil js = new JSchUtil( host[i], Integer.parseInt(port[i])
	 			,SysUtil.decrypt(user[i], "qkrtjqkddhkTsi")
	 			,SysUtil.decrypt(password[i], "qkrtjqkddhkTsi")
	 		);
			
			 List<String> cmd = new ArrayList<String>();
			 cmd.add("ls -al");
			 cmd.add("ls -alh");
			 js.exec(cmd);
			
		}
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	
	@Test
	public void Test_COPY() throws ConfigurationException, URISyntaxException {
		

		logger.debug("-------------------------------------------------------------------------------> start");

		this.prop = new PropertiesConfiguration("server.properties");
		
		boolean debug = prop.getBoolean("debug");
		String[] host = prop.getStringArray("server.host");
		String[] port = prop.getStringArray("server.port");
		String[] user = prop.getStringArray("server.user");
		String[] password = prop.getStringArray("server.password");
		String[] sourcePath = prop.getStringArray("server.source.path");
		String[] targetPath = prop.getStringArray("server.target.path");
		String[] warName = prop.getStringArray("server.war.name");
		
		for( int i = 0; host !=null &&  i < host.length; i++ ) {
			
			JSchUtil js = new JSchUtil( host[i], Integer.parseInt(port[i])
	 			,SysUtil.decrypt(user[i], "qkrtjqkddhkTsi")
	 			,SysUtil.decrypt(password[i], "qkrtjqkddhkTsi")
	 		);
			
			System.out.println("Deploy : " + host[i] + " Start");
			if(!debug) js.disableDebug();
			
			//copy war
			String sourceWar = sourcePath[i] +"/"+ warName[i];
			String targetWar = targetPath[i] +"/"+ warName[i];
			File sourceFile =  new File(sourceWar);
			if(!sourceFile.exists()){
				System.out.println("파일이 존재하지 않습니다");
				return;
			}
			
			System.out.println("File Copy : " + targetWar);
			js.exec("/bin/sh -c  'if [ -d " + targetPath[i] + " ]; then rm -rf " + targetPath[i] + "; fi' ");
			js.exec("/bin/sh -c  'if [ ! -d " + targetPath[i] + " ]; then mkdir " + targetPath[i] + "; fi' ");
			js.scpTo(sourceFile, targetWar);
			
			String[] cmds = {
				"unzip -q " + targetWar + " -d " + targetPath[i]
				,"/bin/sh -c  'if [ -d  " + targetPath[i] + "/META-INF ]; then rm -rf " + targetPath[i] + "/META-INF; fi' "
				,"/bin/sh -c  'if [ -f  " + targetWar + " ]; then rm -f " + targetWar + "; fi' "
				,"ls -al " + targetPath[i]
			};
			js.exec(cmds);
			 
			System.out.println("Stop Tomcat ... ");
			System.out.println("Start Tomcat ... ");
			System.out.println("Deploy : " + host[i] + " End");
		}
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void Test_톰켓_스탑() throws ConfigurationException, URISyntaxException {

		logger.debug("-------------------------------------------------------------------------------> start");
		
		this.prop = new PropertiesConfiguration("server.properties");
		 JSchUtil js = new JSchUtil( prop.getString("server.host")
				 				,prop.getInt("server.port") 
				 				,SysUtil.decrypt(prop.getString("server.user"), "qkrtjqkddhkTsi")
				 				,SysUtil.decrypt(prop.getString("server.password"), "qkrtjqkddhkTsi") );
		  
		 js.exec("sudo /etc/rc.d/init.d/tomcatd stop");
		 
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
	@Test
	public void Test_톰켓_스타트() throws ConfigurationException, URISyntaxException {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		this.prop = new PropertiesConfiguration("server.properties");
		 JSchUtil js = new JSchUtil( prop.getString("server.host")
				 				,prop.getInt("server.port") 
				 				,SysUtil.decrypt(prop.getString("server.user"), "qkrtjqkddhkTsi")
				 				,SysUtil.decrypt(prop.getString("server.password"), "qkrtjqkddhkTsi") );
		
		js.exec("sudo /etc/rc.d/init.d/tomcatd start");
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}

}
