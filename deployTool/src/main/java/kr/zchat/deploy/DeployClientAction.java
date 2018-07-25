package kr.zchat.deploy;

import java.io.File;
import java.net.URISyntaxException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.zchat.deploy.util.JSchUtil;
import kr.zchat.deploy.util.SysUtil;


public class DeployClientAction {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private Configuration prop; 
	
	public DeployClientAction() throws URISyntaxException, ConfigurationException{
		File jarPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
		this.prop = new PropertiesConfiguration(jarPath.getParent() + "/server.properties");
	}
	
	public void deploy(String key) throws URISyntaxException{
		
		boolean debug = prop.getBoolean("debug");
		String[] host = prop.getStringArray("server.host");
		String[] port = prop.getStringArray("server.port");
		String[] user = prop.getStringArray("server.user");
		String[] password = prop.getStringArray("server.password");
		String[] sourcePath = prop.getStringArray("server.source.path");
		String[] targetPath = prop.getStringArray("server.target.path");
		String[] warName = prop.getStringArray("server.war.name");
		
		for( int i = 0; host != null && i < host.length; i++ ) {
			
			//Server Connect
			JSchUtil js = new JSchUtil( 
				host[i]
				,Integer.parseInt(port[i])
	 			,SysUtil.decrypt(user[i], key)
	 			,SysUtil.decrypt(password[i], key)
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
			
			//--------------------------------------------------- Tomcat Stop
			System.out.println("Stop Tomcat ... ");
			String[] cmd1 = {
				"sudo /etc/rc.d/init.d/tomcatd stop"
				,"sleep 2"
			};
			js.exec(cmd1);
			
			
			//--------------------------------------------------- File Copy
			System.out.println("File Copy : " + targetWar);
			js.exec("/bin/sh -c  'if [ -d " + targetPath[i] + " ]; then rm -rf " + targetPath[i] + "; fi' ");
			js.exec("/bin/sh -c  'if [ ! -d " + targetPath[i] + " ]; then mkdir " + targetPath[i] + "; fi' ");
			js.scpTo(sourceFile, targetWar);
			
			String[] cmd2 = {
				"unzip -q " + targetWar + " -d " + targetPath[i]
				,"/bin/sh -c  'if [ -d  " + targetPath[i] + "/META-INF ]; then rm -rf " + targetPath[i] + "/META-INF; fi' "
				,"/bin/sh -c  'if [ -f  " + targetWar + " ]; then rm -f " + targetWar + "; fi' "
			};
			js.exec(cmd2);
			 
			
			//--------------------------------------------------- Tomcat Start
			System.out.println("Start Tomcat ... ");
			String[] cmd3 = {
				"sudo /etc/rc.d/init.d/tomcatd start"
				,"sleep 5"
			};
			js.exec(cmd3);
			
			System.out.println("Deploy : " + host[i] + " End");
			
		}
		
	}
	
	public void copyResource(String war, String target,  JSchUtil js){
	}

	
	public void encrypt(String str, String key){
		System.out.println(SysUtil.encrypt(str, key));
	}
	
	 public boolean keyTest(String key) throws URISyntaxException{
		 return SysUtil.decrypt(prop.getString("host"), key)== null ? false : true ;
	 }
}
