package proje.file;
import org.apache.logging.log4j.*;

public class myTimerLoggings {
	
	
	private static Logger demoLogger = LogManager.getLogger(myTimerLoggings.class.getName());
	public static void main(String[] args) {
		
		for(int i = 0; i < 5; i++) {
			demoLogger.info("info logger");
			demoLogger.error("error logger");
			demoLogger.debug("debug logger");
            try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		

	}

}