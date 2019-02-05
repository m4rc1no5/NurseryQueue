package pl.marceen.nurseryqueueapi.network.control;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.Page;

/**
 * @author Marcin Zaremba
 */
public class RequestBuilder {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String HEADER_NAME_AUTHORIZATION = "Authorization";

    public Request buildRequestForLogin(String json) {
        return new Request.Builder()
                .url(Page.LOGIN.getUrl())
                .post(RequestBody.create(JSON, json))
                .build();
    }

    public Request buildRequestForDictionary(String token) {
        return new Request.Builder()
                .addHeader(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .url(Page.DICTIONARY.getUrl())
                .build();
    }

    public Request buildRequestForOrder(String token, String applicationId) {
        return new Request.Builder()
                .addHeader(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .url(Page.ORDER.getUrl() + applicationId)
                .build();
    }

    public Request buildRequestForConfirmation(String token, String applicationId) {
        return new Request.Builder()
                .addHeader(HEADER_NAME_AUTHORIZATION, getHeaderValueAuthorization(token))
                .url(Page.ORDER.getUrl() + applicationId + "/potwierdz")
                .method("PATCH", RequestBody.create(JSON, "{}"))
                .build();
    }

    private String getHeaderValueAuthorization(String token) {
        return "Bearer " + token;
    }
}
