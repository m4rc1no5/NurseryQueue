package pl.marceen.nurseryqueueapi.crud.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.crud.entity.Result;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Marcin Zaremba
 */
@Stateless
public class ResultManager {
    private static final Logger logger = LoggerFactory.getLogger(ResultManager.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Result result) {
        logger.info("Saving {}", result);
        entityManager.persist(result);
    }
}
