package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import pl.marceen.nurseryqueueapi.crud.entity.Result;
import pl.marceen.nurseryqueueapi.email.entity.EmailData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.Configuration;

import javax.inject.Inject;

/**
 * @author Marcin Zaremba
 */
public class EmailDataBuilder {

    @Inject
    private Configuration configuration;

    @Inject
    private EmailResultTemplate template;

    EmailData build(String emails, Result actualResult, Result lastResult) {
        return new EmailData()
                .emailsFrom(configuration.getEmailFrom())
                .emailsTo(emails)
                .subject(template.getTitle())
                .text(template.getText(actualResult, lastResult));
    }
}
