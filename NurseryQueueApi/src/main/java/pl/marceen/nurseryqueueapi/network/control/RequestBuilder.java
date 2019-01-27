package pl.marceen.nurseryqueueapi.network.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.Page;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * @author Marcin Zaremba
 */
public class RequestBuilder {
    private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);

    private static final String HEADER_NAME_AUTHORIZATION = "Authorization";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    public HttpRequest buildRequestForLogin(String json) {
        return HttpRequest.newBuilder()
                .uri(createUri(Page.LOGIN))
                .timeout(Duration.ofMinutes(1))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
    }

    public HttpRequest buildRequestForDictionary(String token) {
        return HttpRequest.newBuilder()
                .uri(createUri(Page.DICTIONARY))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .build();
    }

    public HttpRequest buildRequestForOrder(String token, String applicationId) {
        return HttpRequest.newBuilder()
                .uri(createOrderUri(applicationId))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .build();
    }

    public HttpRequest buildRequestForConfirmation(String token, String applicationId) {
        return HttpRequest.newBuilder()
                .uri(createOrderUri(applicationId + "/potwierdz"))
                .timeout(Duration.ofMinutes(1))
                .header(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .method("PATCH", HttpRequest.BodyPublishers.ofString("{}"))
                .build();
    }

    private String getHeaderValueAuthorization(String token) {
        return "Bearer " + token;
    }

    private URI createUri(Page page) {
        String url = page.getUrl();
        logger.info("Url: {}", url);

        return URI.create(url);
    }

    private URI createOrderUri(String additionalData) {
        String url = Page.ORDER.getUrl() + additionalData;
        logger.info("Url: {}", url);

        return URI.create(url);
    }
}
