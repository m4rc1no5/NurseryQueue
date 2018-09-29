package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LocalDateRequest;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LocalDateResponse;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

/**
 * @author Marcin Zaremba
 */
public class ProcessResourcesTest {
    private static final Logger logger = LoggerFactory.getLogger(ProcessResourcesTest.class);

    private static final long TIMEOUT_IN_SECONDS = 10L;

    @Test
    @Ignore
    public void checkStatus() {
        ResteasyClient client = new ResteasyClientBuilder()
                .connectionCheckoutTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .establishConnectionTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .socketTimeout(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
                .build();

        LocalDateRequest localDateRequest = new LocalDateRequest();
        localDateRequest.setRequestTime(LocalDate.now());

        ResteasyWebTarget target = client.target("http://10.0.75.2:8080/api/process/check-status");
        Entity<LocalDateRequest> entity = Entity.entity(localDateRequest, MediaType.APPLICATION_JSON);
        Response rawResponse = target.request().post(entity);

        LocalDateResponse localDateResponse = rawResponse.readEntity(LocalDateResponse.class);
        logger.info("LocalDateResponse: {}", localDateResponse);
    }
}