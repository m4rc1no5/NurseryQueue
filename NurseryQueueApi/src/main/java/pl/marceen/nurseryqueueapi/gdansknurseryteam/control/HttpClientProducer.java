package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Produces;
import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class HttpClientProducer {

    @Resource(lookup = "java:jboss/ee/concurrency/executor/default")
    private ManagedExecutorService managedExecutorService;

    @Produces
    public HttpClient produce() {
        return HttpClient.newBuilder()
                .executor(managedExecutorService)
                .build();
    }
}
