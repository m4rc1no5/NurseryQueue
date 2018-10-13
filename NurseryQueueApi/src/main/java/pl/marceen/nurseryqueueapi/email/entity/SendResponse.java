package pl.marceen.nurseryqueueapi.email.entity;

/**
 * @author Marcin Zaremba
 */
public class SendResponse {
    private String status;
    private String description;

    public String getStatus() {
        return status;
    }

    public SendResponse status(String status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public SendResponse description(String description) {
        this.description = description;
        return this;
    }
}
