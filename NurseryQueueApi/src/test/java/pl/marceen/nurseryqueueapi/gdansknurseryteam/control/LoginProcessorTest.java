package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginRequest;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
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
public class LoginProcessorTest {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "pass";
    private static final String JSON = "{}";

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private LoginRequestBuilder loginRequestBuilder;

    @Mock
    private RequestBuilder requestBuilder;

    @Mock
    private HttpExcecutor<LoginResponse> httpExcecutor;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpRequest httpRequest;

    @InjectMocks
    private LoginProcessor sut;

    @Test
    public void login() throws InterruptedException, ParserException, NetworkException {
        //given
        LoginRequest loginRequest = new LoginRequest();
        when(loginRequestBuilder.build(LOGIN, PASSWORD)).thenReturn(loginRequest);
        when(requestBuilder.buildRequestForLogin(JSON)).thenReturn(httpRequest);

        //when
        sut.login(httpClient, LOGIN, PASSWORD);

        //then
        verify(httpExcecutor).execute(LoginResponse.class, httpClient, httpRequest);
    }
}