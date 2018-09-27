package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import javax.annotation.Resource;

/**
 * @author Marcin Zaremba
 */
public class Configuration {

    @Resource(name = "java:global/emailFrom")
    private String emailFrom;

    public String getEmailFrom() {
        return emailFrom;
    }
}
