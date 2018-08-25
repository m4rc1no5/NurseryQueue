package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.network.control.HttpExcecutor;
import pl.marceen.network.control.RequestBuilder;
import pl.marceen.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DictionaryResponse;

import javax.inject.Inject;

/**
 * @author Marcin Zaremba
 */
public class DictionaryProcessor {
    private static final Logger logger = LoggerFactory.getLogger(DictionaryProcessor.class);

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<DictionaryResponse> httpExcecutor;

    public DictionaryResponse process(OkHttpClient client, String token) throws NetworkException {
        logger.info("Try to get dictionary");

        Request request = requestBuilder.buildRequestForDictionary(token);
        return httpExcecutor.execute(DictionaryResponse.class, client, request);
    }
}
