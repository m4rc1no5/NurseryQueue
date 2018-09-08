package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;

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

    public OrderResponse process(String login, String password) throws NetworkException, ParserException {
        OkHttpClient client = new OkHttpClient();

        logger.info("Login");
        LoginResponse loginResponse = loginProcessor.login(client, login, password);

        String token = loginResponse.getToken();
        logger.info("Token: {}", token);

        logger.info("Getting dictionary");
        dictionaryProcessor.process(client, token);

        logger.info("Getting order");
        return orderProcessor.process(client, token);
    }
}
