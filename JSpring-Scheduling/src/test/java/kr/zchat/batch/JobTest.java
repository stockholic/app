package kr.zchat.batch;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JobTest extends BaseTestCase{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Test
	public void job() throws Exception {
		
		logger.debug("-------------------------------------------------------------------------------> start");
		
		
		logger.debug("-------------------------------------------------------------------------------> end");
	}
	
}

