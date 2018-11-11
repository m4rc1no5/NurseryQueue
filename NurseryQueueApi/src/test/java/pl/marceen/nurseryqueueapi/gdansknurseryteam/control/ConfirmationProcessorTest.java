package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ConfirmationData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class ConfirmationProcessorTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private ConfirmationProcessor sut;

    private ConfirmationData confirmationData;

    @Before
    public void setUp() {
        confirmationData = new ConfirmationData()
                .httpClient(httpClient)
                .token("123abc")
                .nextConfirmationFrom("2018-11-20");
    }

    @Test
    public void confirm() throws NetworkException, ParserException, InterruptedException {
        //given

        //when
        sut.confirm(confirmationData);

        //then

    }
}