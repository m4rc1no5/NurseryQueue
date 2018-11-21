package pl.marceen.nurseryqueueapi.crud.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.entity.Client;
import pl.marceen.nurseryqueueapi.crud.entity.Result;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ResultManager {
    private static final Logger logger = LoggerFactory.getLogger(ResultManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public List<Result> findAllByClient(Client client, int limit) {
        logger.info("Try to find {} results by client: {}", limit, client.getLogin());

        return entityManager.createNamedQuery(Result.FIND_ALL_BY_CLIENT, Result.class)
                .setParameter("client", client)
                .setMaxResults(limit)
                .getResultList();
    }

    public void save(Result result) {
        logger.info("Saving {}", result);
        entityManager.persist(result);
    }
}
