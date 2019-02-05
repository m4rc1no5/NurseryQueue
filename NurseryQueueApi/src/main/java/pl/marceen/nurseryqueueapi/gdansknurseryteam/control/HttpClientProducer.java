package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;

import javax.enterprise.inject.Produces;

/**
 * @author Marcin Zaremba
 */
public class HttpClientProducer {

    @Produces
    public OkHttpClient produce() {
        return new OkHttpClient();
    }
}
