package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DictionaryResponse;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;
import java.net.http.HttpClient;

/**
 * @author Marcin Zaremba
 */
public class DictionaryProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryProcessor.class);

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<DictionaryResponse> httpExcecutor;

    public DictionaryResponse process(HttpClient client, String token) throws NetworkException {
        logger.info("Try to get dictionary");

        return httpExcecutor.execute(DictionaryResponse.class, client, requestBuilder.buildRequestForDictionary(token));
    }
}
