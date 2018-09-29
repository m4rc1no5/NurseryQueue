package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.ProcessFacade;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LocalDateRequest;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LocalDateResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;

/**
 * @author Marcin Zaremba
 */
@Stateless
@Path("process")
public class ProcessResources {
    private static final Logger logger = LoggerFactory.getLogger(ProcessResources.class);

    @Inject
    private ProcessFacade processFacade;

    @POST
    @Path("check-status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public LocalDateResponse checkStatus(LocalDateRequest request) {
        logger.info("request: {}", request);

        long currentTimeMillis = System.currentTimeMillis();
        logger.info("Time: {}", currentTimeMillis);

        LocalDateResponse response = new LocalDateResponse();
        response.setResponseTime(LocalDate.now());

        return response;
    }

    @GET
    @Path("check-order/{login}/{password}")
    public String checkOrder(@PathParam("login") String login, @PathParam("password") String password) throws NetworkException, ParserException {
        OrderResponse orderResponse = processFacade.process(login, password);

        return orderResponse.getFirstNurseryName() + " - " + orderResponse.getFirstNurseryStanding();
    }
}
