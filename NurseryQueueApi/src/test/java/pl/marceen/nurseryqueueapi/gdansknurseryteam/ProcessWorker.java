package pl.marceen.nurseryqueueapi.gdansknurseryteam;

import org.junit.Before;
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
import pl.marceen.nurseryqueueapi.FileReader;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.*;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;

import javax.json.bind.JsonbBuilder;
import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class ProcessWorker {
    private static final Logger logger = LoggerFactory.getLogger(ProcessWorker.class);

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
    private TokenDecoder tokenDecoder;

    @InjectMocks
    private LoginProcessor loginProcessor;

    @InjectMocks
    private DictionaryProcessor dictionaryProcessor;

    @InjectMocks
    private OrderProcessor orderProcessor;

    @Before
    public void setUp() {
        orderProcessor.setTokenDecoder(tokenDecoder);
    }

    @Test
    @Ignore
    public void process() throws Exception {
        logger.info("Process START");

        var json = FileReader.read(getClass(), "config/gdansk_nursery_team.yaml");
        var gdanskNurseryTeamConfig = convertConfig(json);

        var httpClient = HttpClient.newBuilder()
                .build();

        logger.info("Login");
        var loginResponse = loginProcessor.login(httpClient, gdanskNurseryTeamConfig.getLogin(), gdanskNurseryTeamConfig.getPassword());

        var token = loginResponse.getToken();
        logger.info("Token: {}", token);

        logger.info("Getting dictionary");
        dictionaryProcessor.process(httpClient, token);

        logger.info("Getting order");
        var orderResponse = orderProcessor.process(httpClient, token);
        logger.info("nursery #1: {} - {}", orderResponse.getFirstNurseryName(), orderResponse.getFirstNurseryStanding());
        logger.info("nursery #2: {} - {}", orderResponse.getSecondNurseryName(), orderResponse.getSecondNurseryStanding());
        logger.info("nursery #3: {} - {}", orderResponse.getThirdNurseryName(), orderResponse.getThirdNurseryStanding());

        logger.info("Process STOP");
    }

    private GdanskNurseryTeamConfig convertConfig(String json) {
        logger.info("Try to convert json {} to GdanskNurseryTeamConfig", json);

        return JsonbBuilder.create().fromJson(json, GdanskNurseryTeamConfig.class);
    }
}
