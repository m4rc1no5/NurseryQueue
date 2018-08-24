package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import pl.marceen.network.control.HttpExcecutor;
import pl.marceen.network.control.RequestBuilder;
import pl.marceen.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DictionaryResponse;

import javax.inject.Inject;

/**
 * @author Marcin Zaremba
 */
public class DictionaryProcessor {

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<DictionaryResponse> httpExcecutor;

    public DictionaryResponse process(OkHttpClient client, String token) throws NetworkException {
        Request request = requestBuilder.buildRequestForDictionary(token);
        return httpExcecutor.execute(DictionaryResponse.class, client, request);
    }
}
