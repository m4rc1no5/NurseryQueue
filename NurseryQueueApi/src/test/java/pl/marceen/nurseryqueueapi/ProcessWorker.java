package pl.marceen.nurseryqueueapi;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Marcin Zaremba
 */
public class ProcessWorker {
    private static final Logger logger = LoggerFactory.getLogger(ProcessWorker.class);

    @Test
    @Ignore
    public void process() {
        logger.info("Process START");

        logger.info("Process STOP");
    }
}
