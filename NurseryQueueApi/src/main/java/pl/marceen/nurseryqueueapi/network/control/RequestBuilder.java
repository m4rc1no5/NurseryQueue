package pl.marceen.nurseryqueueapi.network.control;

import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.Page;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * @author Marcin Zaremba
 */
public class RequestBuilder {
    private static final String HEADER_NAME_AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public HttpRequest buildRequestForLogin(String json) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Page.LOGIN.getUrl()))
                .timeout(Duration.ofMinutes(1))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public HttpRequest buildRequestForDictionary(String token) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Page.DICTIONARY.getUrl()))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .build();
    }

    public HttpRequest buildRequestForOrder(String token, String applicationId) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Page.ORDER.getUrl() + applicationId))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .build();
    }

    public HttpRequest buildRequestForConfirmation(String token, String applicationId) {
        return HttpRequest.newBuilder()
                .uri(URI.create(Page.ORDER.getUrl() + applicationId + "/potwierdz"))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .method("PATCH", HttpRequest.BodyPublishers.ofString("{}"))
                .build();
    }

    private String getHeaderValueAuthorization(String token) {
        return "Bearer " + token;
    }
}
