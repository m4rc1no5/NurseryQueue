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
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.*;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;

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

    @Mock(answer = Answers.CALLS_REAL_METHODS)
    private Base64Decoder base64Decoder;

    @InjectMocks
    private LoginProcessor loginProcessor;

    @InjectMocks
    private DictionaryProcessor dictionaryProcessor;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Test
    @Ignore
    public void process() throws NetworkException, ParserException {
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
