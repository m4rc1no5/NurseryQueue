package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;
import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class ProcessFacade {
    private static final Logger logger = LoggerFactory.getLogger(ProcessFacade.class);

    @Inject
    private LoginProcessor loginProcessor;

    @Inject
    private DictionaryProcessor dictionaryProcessor;

    @Inject
    private OrderProcessor orderProcessor;

    @Inject
    private ConfirmationProcessor confirmationProcessor;

    @Inject
    private HttpClient httpClient;

    public OrderResponse process(String login, String password) throws NetworkException, ParserException, InterruptedException {
        logger.info("Login");
        var loginResponse = loginProcessor.login(httpClient, login, password);

        var token = loginResponse.getToken();
        logger.info("Token: {}", token);

        logger.info("Getting dictionary");
        dictionaryProcessor.process(httpClient, token);

        logger.info("Getting order");
        var orderResponse = orderProcessor.process(httpClient, token);

        logger.info("Confirming order");
        confirmationProcessor.confirm(new ConfirmationData()
                .httpClient(httpClient)
                .token(token)
                .nextConfirmationFrom(orderResponse.getNextConfirmationFrom())
        );

        return orderResponse;
    }
}
