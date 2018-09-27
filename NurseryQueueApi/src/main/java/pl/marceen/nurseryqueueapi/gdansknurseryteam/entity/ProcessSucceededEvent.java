package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import pl.marceen.nurseryqueueapi.crud.entity.Client;

/**
 * @author Marcin Zaremba
 */
public class ProcessSucceededEvent {
    private Client client;

    public ProcessSucceededEvent(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }
}
