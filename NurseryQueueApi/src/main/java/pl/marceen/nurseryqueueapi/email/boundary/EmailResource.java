package pl.marceen.nurseryqueueapi.email.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.email.control.EmailSender;
import pl.marceen.nurseryqueueapi.email.entity.EmailData;
import pl.marceen.nurseryqueueapi.email.entity.EmailException;
import pl.marceen.nurseryqueueapi.email.entity.SendResponse;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author Marcin Zaremba
 * <p>
 * Przykładowe użycie:
 * curl -i -H "Accept: application/json" -H "Content-type: application/json" -X POST -d '{"emailsFrom": "from@host.pl","emailsTo":"marcin.zaremba@gmail.com",
 * "subject": "Testowy mail","text": "kabooom!"}' http://localhost:8080/api/email/send
 */
@Stateless
@Path("email")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmailResource {
    private static final Logger logger = LoggerFactory.getLogger(EmailResource.class);

    @Inject
    private EmailSender emailSender;

    @POST
    @Path("send")
    public SendResponse send(EmailData request) {
        logger.info("Sending email");

        SendResponse response = new SendResponse();
        try {
            emailSender.send(request);

            return response.status("OK");
        } catch (EmailException e) {
            return response.status("ERROR")
                    .description(e.getMessage());
        }
    }
}
