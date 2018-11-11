package pl.marceen.nurseryqueueapi.gdansknurseryteam.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.control.ClientManager;
import pl.marceen.nurseryqueueapi.crud.control.ResultManager;
import pl.marceen.nurseryqueueapi.crud.entity.Client;
import pl.marceen.nurseryqueueapi.crud.entity.Result;
import pl.marceen.nurseryqueueapi.crud.entity.Service;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.control.ProcessFacade;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ProcessSucceededEvent;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ProcessTimer {
    private static final Logger logger = LoggerFactory.getLogger(ProcessTimer.class);

    private static final String PROCESS_FAILED = "Process failed - message: {}";

    @Inject
    private ClientManager clientManager;

    @Inject
    private ProcessFacade processFacade;

    @Inject
    private ResultManager resultManager;

    @Inject
    private Event<ProcessSucceededEvent> processSucceededEventEvent;

    @Schedule(persistent = false, hour = "*")
    public void process() {
        logger.info("Process START");

        List<Client> clientList = clientManager.findAllActiveByService(Service.GDANSK_NURSERY_TEAM);
        logger.info("Clients to parse: {}", clientList.size());

        clientList.forEach(this::parseAndSave);

        logger.info("Process STOP");
    }

    private void parseAndSave(Client client) {
        parse(client.getLogin(), client.getPassword())
                .ifPresent(orderResponse -> {
                    resultManager.save(map(client, orderResponse));
                    processSucceededEventEvent.fire(new ProcessSucceededEvent(client));
                });
    }

    private Optional<OrderResponse> parse(String login, String password) {
        logger.info("Parsing {}", login);

        try {
            return Optional.of(processFacade.process(login, password));
        } catch (NetworkException | ParserException e) {
            logger.error(PROCESS_FAILED, e.getMessage());

            return Optional.empty();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(PROCESS_FAILED, e.getMessage());

            return Optional.empty();
        }
    }

    private Result map(Client client, OrderResponse orderResponse) {
        Result result = new Result();
        result.setClient(client);
        result.setFirstNurseryName(orderResponse.getFirstNurseryName());
        result.setFirstNurseryStanding(Integer.valueOf(orderResponse.getFirstNurseryStanding()));

        result.setSecondNurseryName(orderResponse.getSecondNurseryName());
        result.setSecondNurseryStanding(Integer.valueOf(orderResponse.getSecondNurseryStanding()));

        result.setThirdNurseryName(orderResponse.getThirdNurseryName());
        result.setThirdNurseryStanding(Integer.valueOf(orderResponse.getThirdNurseryStanding()));

        return result;
    }
}
