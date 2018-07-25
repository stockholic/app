package kr.zchat.deploy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DeployClientHandler {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
    //암호키
    @Option(name="-k", usage="암호키", metaVar ="input")
    private String  key;
    
    //암호 문자열
    @Option(name="-e", usage="암호화 문자열", metaVar="input")
    private String  encrypt;
    
    //jenkins deploy
    @Option(name="-w")
    private boolean war;

    
    // receives other command line parameters than options
    @Argument
    private List<String> arguments = new ArrayList<String>();

	 @SuppressWarnings("deprecation")
	public void cmd(String[] args) throws IOException, URISyntaxException, ConfigurationException {
		 
		 CmdLineParser parser = new CmdLineParser(this);
		 parser.setUsageWidth(200);
	        try {
	            parser.parseArgument(args);
	        } catch( CmdLineException e ) {
	            usage(parser);
	            return;
	        }
	        
	        DeployClientAction rca = new DeployClientAction();
	        
	        // deploy 
	        if(war && key != null){
	        	logger.debug("key : " + key);
	        	rca.deploy(key);
	        
	        //암호화
	        }else if(encrypt != null && key != null){
	        	logger.debug("encrypt : " + encrypt);
	        	logger.debug("key : " + key);
	        	rca.encrypt(encrypt, key);
	        }else{
	        	usage(parser);
	        }
	        
	}
	 
	
	 public void usage(CmdLineParser parser){
    	 System.out.print("Uage :\n");
    	 System.out.println("java -jar deployTool.jar [options...] arguments...");
    	 parser.printUsage(System.out);
    	 System.out.println();
    	 System.out.println("Example:");
    	 System.out.println("encrypt :\t java -jar deployTool.jar -e 문자열 -k 암호키");
    	 System.out.println("deploy :\t java -jar deployTool.jar -w  -k XXX");
    }
	 
	
}
