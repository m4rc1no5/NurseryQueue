package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import okhttp3.OkHttpClient;

/**
 * @author Marcin Zaremba
 * // TODO: 2018-11-06 do usunięcia jeśli zostaną tylko trzy atrybuty
 */
public class ConfirmationData {
    private OkHttpClient httpClient;
    private String token;
    private String nextConfirmationFrom;

    public OkHttpClient getHttpClient() {
        return httpClient;
    }

    public ConfirmationData httpClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        return this;
    }

    public String getToken() {
        return token;
    }

    public ConfirmationData token(String token) {
        this.token = token;
        return this;
    }

    public String getNextConfirmationFrom() {
        return nextConfirmationFrom;
    }

    public ConfirmationData nextConfirmationFrom(String nextConfirmationFrom) {
        this.nextConfirmationFrom = nextConfirmationFrom;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfirmationData{");
        sb.append("httpClient=").append(httpClient);
        sb.append(", token='").append(token).append('\'');
        sb.append(", nextConfirmationFrom='").append(nextConfirmationFrom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
