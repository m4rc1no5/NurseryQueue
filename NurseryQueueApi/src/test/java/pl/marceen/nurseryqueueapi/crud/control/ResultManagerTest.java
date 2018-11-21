package pl.marceen.nurseryqueueapi.crud.control;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.nurseryqueueapi.crud.entity.Client;
import pl.marceen.nurseryqueueapi.crud.entity.Result;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Marcin Zaremba
 */
public class ResultManagerTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery typedQuery;

    @InjectMocks
    private ResultManager sut;


    @Test
    public void findAllByClient() {
        //given
        var client = new Client();
        int limit = 1;
        when(entityManager.createNamedQuery(Result.FIND_ALL_BY_CLIENT, Result.class)).thenReturn(typedQuery);
        when(typedQuery.setParameter("client", client)).thenReturn(typedQuery);
        when(typedQuery.setMaxResults(limit)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(Collections.emptyList());

        //when
        List<Result> resultList = sut.findAllByClient(client, limit);

        //then
        assertThat(resultList.size()).isEqualTo(0);
    }

    @Test
    public void save() {
        //given
        var result = new Result();

        //when
        sut.save(result);

        //then
        verify(entityManager).persist(result);
    }
}