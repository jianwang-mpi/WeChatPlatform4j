package log4j;

import org.apache.log4j.Logger;

/**
 * Created by Alchemist on 2016/4/24.
 */
public class Log4j {
    public void log() {
        Logger logger  =  Logger.getLogger(Log4j.class.getName());
        logger.debug("log4j debug");
        logger.info("log4j info");
        logger.trace("log4j trace");
        logger.warn("log4j warn");
        logger.fatal("log4j fatal");
        logger.error("log4j error");
    }
    public void infolog(String message){
        Logger logger  =  Logger.getLogger(Log4j.class.getName());
        logger.info(message);
    }
}
