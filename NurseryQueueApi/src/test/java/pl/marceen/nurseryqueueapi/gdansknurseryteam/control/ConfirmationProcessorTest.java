package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DecodedData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Marcin Zaremba
 */
public class ConfirmationProcessorTest {

    private static final String TOKEN = "123abc";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpRequest httpRequest;

    @Mock
    private RequestBuilder requestBuilder;

    @Mock
    private TokenDecoder tokenDecoder;

    @Mock
    private HttpExcecutor<OrderResponse> httpExcecutor;

    @InjectMocks
    private ConfirmationProcessor sut;

    private ConfirmationData confirmationData;

    @Before
    public void setUp() {
        confirmationData = new ConfirmationData()
                .httpClient(httpClient)
                .token(TOKEN)
                .nextConfirmationFrom("2018-11-20");
    }

    @Test
    public void confirm() throws NetworkException, ParserException, InterruptedException {
        //given
        DecodedData decodedData = new DecodedData();
        String applicationId = "appId";
        decodedData.setApplicationId(applicationId);
        when(tokenDecoder.decode(TOKEN)).thenReturn(decodedData);
        when(requestBuilder.buildRequestForConfirmation(TOKEN, applicationId)).thenReturn(httpRequest);

        //when
        sut.confirm(confirmationData);

        //then
        verify(httpExcecutor).execute(OrderResponse.class, httpClient, httpRequest);
    }
}