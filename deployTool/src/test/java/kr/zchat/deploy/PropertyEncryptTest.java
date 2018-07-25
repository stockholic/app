package kr.zchat.deploy;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.zchat.deploy.util.SysUtil;

public class PropertyEncryptTest {

	Logger logger = LoggerFactory.getLogger(getClass());

	private String host = "localhost";
	private String user = "user";
	private String password = "1234";
	private String key = "1234";
	

	@Test
	public void encryptTest() {

		logger.debug("-------------------------------------------------------------------------------> start");

		logger.debug(SysUtil.encrypt(host, key));
		logger.debug(SysUtil.encrypt(user, key));
		logger.debug(SysUtil.encrypt(password, key));

		logger.debug("-------------------------------------------------------------------------------> end");
	}
}
