package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.net.http.HttpClient;

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

    public LoginResponse login(HttpClient client, String login, String password) throws ParserException, NetworkException, InterruptedException {
        var json = getRequestInJson(login, password);
        logger.info("LoginRequest: {}", json);

        return httpExcecutor.execute(LoginResponse.class, client, requestBuilder.buildRequestForLogin(json));
    }

    private String getRequestInJson(String login, String password) throws ParserException {
        try (var jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(loginRequestBuilder.build(login, password));
        } catch (Exception e) {
            throw ParserException.problemWithBuildingJsonb(e.getMessage(), logger);
        }
    }
}
