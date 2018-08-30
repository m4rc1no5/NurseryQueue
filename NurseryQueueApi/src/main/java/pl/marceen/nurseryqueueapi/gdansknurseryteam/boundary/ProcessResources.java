package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.DictionaryProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.LoginProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.OrderProcessor;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * @author Marcin Zaremba
 */
@Stateless
@Path("process")
public class ProcessResources {
    private static final Logger logger = LoggerFactory.getLogger(ProcessResources.class);

    @Inject
    private LoginProcessor loginProcessor;

    @Inject
    private DictionaryProcessor dictionaryProcessor;

    @Inject
    private OrderProcessor orderProcessor;

    @GET
    @Path("check-status")
    public String checkStatus() {
        logger.info("Time: {}", System.currentTimeMillis());

        return "kaboooom!";
    }

    @GET
    @Path("check-order/{login}/{password}")
    public String checkOrder(@PathParam("login") String login, @PathParam("password") String password) throws NetworkException, ParserException {
        OkHttpClient client = new OkHttpClient();

        logger.info("Login");
        LoginResponse loginResponse = loginProcessor.login(client, login, password);

        String token = loginResponse.getToken();
        logger.info("Token: {}", token);

        logger.info("Getting dictionary");
        dictionaryProcessor.process(client, token);

        logger.info("Getting order");
        OrderResponse orderResponse = orderProcessor.process(client, token);

        return orderResponse.getFirstNurseryName() + " - " + orderResponse.getFirstNurseryStanding();
    }
}
