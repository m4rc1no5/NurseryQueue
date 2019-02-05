package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationSucceededEvent;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;

/**
 * @author Marcin Zaremba
 * // TODO: 2019-02-05 unit tests
 */
@Stateless
public class ConfirmationObserver {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationObserver.class);

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void confirmationSucceeded(@Observes(during = TransactionPhase.AFTER_SUCCESS) ConfirmationSucceededEvent event) {
        logger.info("Sending email with information about confirmation");
    }
}
