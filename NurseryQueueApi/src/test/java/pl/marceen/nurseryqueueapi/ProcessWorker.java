package pl.marceen.nurseryqueueapi;

import okhttp3.OkHttpClient;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.network.control.HttpExcecutor;
import pl.marceen.network.control.RequestBuilder;
import pl.marceen.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.DictionaryProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.LoginProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.LoginRequestBuilder;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.OrderProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;

/**
 * @author Marcin Zaremba
 */
public class ProcessWorker {
    private static final Logger logger = LoggerFactory.getLogger(ProcessWorker.class);

    private static final String LOGIN = "";
    private static final String PASSWORD = "";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private LoginRequestBuilder loginRequestBuilder;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private RequestBuilder requestBuilder;

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private HttpExcecutor<LoginResponse> httpExcecutor;

    @InjectMocks
    private LoginProcessor loginProcessor;

    @InjectMocks
    private DictionaryProcessor dictionaryProcessor;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Test
    @Ignore
    public void process() throws NetworkException {
        logger.info("Process START");

        OkHttpClient client = new OkHttpClient();

        logger.info("Login");
        LoginResponse loginResponse = loginProcessor.login(client, LOGIN, PASSWORD);

        String token = loginResponse.getToken();
        logger.info("Token: {}", token);

        logger.info("Getting dictionary");
        dictionaryProcessor.process(client, token);

        logger.info("Getting order");
        orderProcessor.process(client, token);

        logger.info("Process STOP");
    }
}
