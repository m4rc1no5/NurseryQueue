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

    private static final Pattern PATTERN = Pattern.compile("^(\\{\"alg\":\"HS256\",\"typ\":\"JWS\"})(\\{.*})");

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<OrderResponse> httpExcecutor;

    @Inject
    private Base64Decoder base64Decoder;

    public OrderResponse process(HttpClient client, String token) throws NetworkException, ParserException {
        var decodedData = getDecodedData(token);
        logger.info(decodedData.toString());

        return httpExcecutor.execute(OrderResponse.class, client, requestBuilder.buildRequestForOrder(token, decodedData.getApplicationId()));
    }

    private DecodedData getDecodedData(String token) throws ParserException {
        logger.info("Try to get decoded data");

        var matcher = PATTERN.matcher(base64Decoder.decode(token));

        if (!matcher.find()) {
            throw ParserException.decodedDataNotFound(logger);
        }

        var decodedDataAsJson = matcher.group(2);
        logger.info("Decoded data as Json: {}", decodedDataAsJson);

        return convertToDecodedData(decodedDataAsJson);
    }

    private DecodedData convertToDecodedData(String json) throws ParserException {
        try {
            return JsonbBuilder.create().fromJson(json, DecodedData.class);
        } catch (JsonbException e) {
            throw ParserException.decodedDataNotFound(logger);
        }
    }
}
