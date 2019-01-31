package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import javax.enterprise.inject.Produces;
import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class HttpClientProducer {

    @Produces
    public HttpClient produce() {
        return HttpClient.newBuilder()
                .build();
    }
}
