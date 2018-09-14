package pl.marceen.nurseryqueueapi.crud.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.entity.Client;
import pl.marceen.nurseryqueueapi.crud.entity.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ClientManager {
    private static final Logger logger = LoggerFactory.getLogger(ClientManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Client> findAllActiveByService(Service service) {
        logger.info("Try to find Client list by service {}", service);

        return entityManager.createNamedQuery(Client.FIND_ALL_ACTIVE_BY_SERVICE, Client.class)
                .setParameter("service", service)
                .getResultList();
    }
}
