package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.network.control.HttpExcecutor;
import pl.marceen.network.control.RequestBuilder;
import pl.marceen.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginRequest;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;

/**
 * @author Marcin Zaremba
 */
public class LoginProcessor {
    private static final Logger logger = LoggerFactory.getLogger(LoginProcessor.class);

    @Inject
    private LoginRequestBuilder loginRequestBuilder;

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<LoginResponse> httpExcecutor;

    public LoginResponse login(OkHttpClient client, String login, String password) throws NetworkException {
        LoginRequest loginRequest = loginRequestBuilder.build(login, password);

        String json = JsonbBuilder.create().toJson(loginRequest);
        logger.info("LoginRequest: {}", json);

        Request request = requestBuilder.buildRequestForLogin(json);
        return httpExcecutor.execute(LoginResponse.class, client, request);
    }
}
