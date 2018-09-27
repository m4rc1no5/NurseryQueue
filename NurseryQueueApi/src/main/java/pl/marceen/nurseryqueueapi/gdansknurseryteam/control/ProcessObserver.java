package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.control.ResultManager;
import pl.marceen.nurseryqueueapi.crud.entity.Client;
import pl.marceen.nurseryqueueapi.crud.entity.Result;
import pl.marceen.nurseryqueueapi.email.control.EmailSender;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ProcessSucceededEvent;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import java.util.List;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ProcessObserver {
    private static final Logger logger = LoggerFactory.getLogger(ProcessObserver.class);

    private static final int LIMIT = 2;

    @Inject
    private ResultManager resultManager;

    @Inject
    private EmailSender emailSender;

    @Inject
    private EmailDataBuilder emailDataBuilder;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void processSucceeded(@Observes(during = TransactionPhase.AFTER_SUCCESS) ProcessSucceededEvent event) {
        Client client = event.getClient();
        logger.info("Process has been successfully completed for client: {}", client.getLogin());

        List<Result> resultList = resultManager.findAllByClient(client, LIMIT);

        if (resultList.size() < LIMIT) {
            logger.info("No results to compare");
            return;
        }

        logger.info("Comparing two last results");
        Result actualResult = resultList.get(0);
        Result lastResult = resultList.get(1);

        if (isChangeOfStandings(actualResult, lastResult)) {
            logger.info("Found difference beetween last two results - sending email");

            emailSender.send(emailDataBuilder.build(client.getEmails(), actualResult, lastResult));
            return;
        }

        logger.info("Nothing has changed");
    }

    private boolean isChangeOfStandings(Result actualResult, Result lastResult) {
        return !actualResult.getFirstNurseryStanding().equals(lastResult.getFirstNurseryStanding()) ||
                !actualResult.getSecondNurseryStanding().equals(lastResult.getSecondNurseryStanding()) ||
                !actualResult.getThirdNurseryStanding().equals(lastResult.getThirdNurseryStanding());
    }
}
