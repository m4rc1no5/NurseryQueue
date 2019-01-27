package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DecodedData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;
import pl.marceen.nurseryqueueapi.network.control.HttpExcecutor;
import pl.marceen.nurseryqueueapi.network.control.RequestBuilder;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import java.net.http.HttpClient;
import java.util.regex.Pattern;

/**
 * @author Marcin Zaremba
 */
public class OrderProcessor {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private TokenDecoder tokenDecoder;

    @Inject
    private HttpExcecutor<OrderResponse> httpExcecutor;

    public OrderResponse process(HttpClient client, String token) throws NetworkException, ParserException, InterruptedException {
        logger.info("Ordering");

        return httpExcecutor.execute(OrderResponse.class, client, requestBuilder.buildRequestForOrder(token, tokenDecoder.decode(token).getApplicationId()));
    }

    public void setTokenDecoder(TokenDecoder tokenDecoder) {
        this.tokenDecoder = tokenDecoder;
    }
}
