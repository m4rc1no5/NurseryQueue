package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.*;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.enterprise.event.Event;

import static org.mockito.ArgumentMatchers.any;
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
    private OkHttpClient httpClient;

    @Mock
    private RequestBuilder requestBuilder;

    @Mock
    private TokenDecoder tokenDecoder;

    @Mock
    private HttpExcecutor<OrderResponse> httpExcecutor;

    @Mock
    private Event<ConfirmationSucceededEvent> confirmationSucceededEvent;

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
    public void confirm() throws NetworkException, ParserException {
        //given
        DecodedData decodedData = new DecodedData();
        String applicationId = "appId";
        decodedData.setApplicationId(applicationId);
        Request request = new Request.Builder().url(Page.DICTIONARY.getUrl()).build();
        when(tokenDecoder.decode(TOKEN)).thenReturn(decodedData);
        when(requestBuilder.buildRequestForConfirmation(TOKEN, applicationId)).thenReturn(request);

        //when
        sut.confirm(confirmationData);

        //then
        verify(httpExcecutor).execute(OrderResponse.class, httpClient, request);
        verify(confirmationSucceededEvent).fire(any(ConfirmationSucceededEvent.class));
    }
}