package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * @author Marcin Zaremba
 */
public class ConfirmationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationProcessor.class);

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private TokenDecoder tokenDecoder;

    @Inject
    private HttpExcecutor<OrderResponse> httpExcecutor;

    void confirm(ConfirmationData data) throws ParserException, NetworkException, InterruptedException {
        logger.info("Confirmation START");

        LocalDate nextConfirmation = LocalDate.parse(data.getNextConfirmationFrom());
        if (nextConfirmation.isAfter(LocalDate.now())) {
            logger.info("Confirmation unnecessary");
            return;
        }

        logger.info("Confirming");
        httpExcecutor.execute(
                OrderResponse.class,
                data.getHttpClient(),
                requestBuilder.buildRequestForConfirmation(data.getToken(), tokenDecoder.decode(data.getToken()).getApplicationId())
        );

        logger.info("Confirmation STOP");
    }
}
