package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.ProcessFacade;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ProcessTimer {
    private static final Logger logger = LoggerFactory.getLogger(ProcessTimer.class);

    private static final String LOGIN = "";
    private static final String PASSWORD = "";

    @Inject
    private ProcessFacade processFacade;

    @Schedule(persistent = false, hour = "*")
    public void process() {
        logger.info("Process START");

        try {
            processFacade.process(LOGIN, PASSWORD);
        } catch (NetworkException | ParserException e) {
            logger.error("Process failed - message: {}", e.getMessage());
            return;
        }

        logger.info("Process STOP");
    }
}
