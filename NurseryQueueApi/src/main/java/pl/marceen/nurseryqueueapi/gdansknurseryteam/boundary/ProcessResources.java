package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.ProcessFacade;
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
    private ProcessFacade processFacade;

    @GET
    @Path("check-status")
    public String checkStatus() {
        logger.info("Time: {}", System.currentTimeMillis());

        return "kaboooom!";
    }

    @GET
    @Path("check-order/{login}/{password}")
    public String checkOrder(@PathParam("login") String login, @PathParam("password") String password) throws NetworkException, ParserException {
        OrderResponse orderResponse = processFacade.process(login, password);

        return orderResponse.getFirstNurseryName() + " - " + orderResponse.getFirstNurseryStanding();
    }
}
