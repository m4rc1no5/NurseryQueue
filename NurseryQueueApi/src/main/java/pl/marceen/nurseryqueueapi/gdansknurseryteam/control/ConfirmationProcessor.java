package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationData;

import java.time.LocalDate;

/**
 * @author Marcin Zaremba
 */
public class ConfirmationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationProcessor.class);

    void confirm(ConfirmationData data) {
        logger.info("Confirmation START");
        logger.info(data.toString());


        // TODO: 2018-11-06 sprawdzenie czy możliwe jest sparsowanie daty
        LocalDate nextConfirmation = LocalDate.parse(data.getNextConfirmationFrom());
        if (nextConfirmation.isAfter(LocalDate.now())) {
            logger.info("Confirmation unnecessary");
            return;
        }

        logger.info("Confirming");

        // TODO: 2018-11-06 potwiedzamy zamówienie jeśli jest już odpowiedni moment

        // TODO: 2018-11-06 wysyłamy maila z informacją o tym, że potwiedzenie wykonało się poprawnie

        logger.info("Confirmation STOP");
    }
}
