package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.network.control.HttpExcecutor;
import pl.marceen.network.control.RequestBuilder;
import pl.marceen.network.entity.NetworkException;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DecodedData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.OrderResponse;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marcin Zaremba
 */
public class OrderProcessor {
    private static final Logger logger = LoggerFactory.getLogger(OrderProcessor.class);

    @Inject
    private RequestBuilder requestBuilder;

    @Inject
    private HttpExcecutor<OrderResponse> httpExcecutor;

    public OrderResponse process(OkHttpClient client, String token) throws NetworkException {
        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(token);
        String decodedDataAsString = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Decoded base64: {}", decodedDataAsString);

        Pattern pattern = Pattern.compile("^(\\{\"alg\":\"HS256\",\"typ\":\"JWS\"})(\\{.*})");
        Matcher matcher = pattern.matcher(decodedDataAsString);

        if (!matcher.find()) {
            logger.error("Problem with decoding base64");
            return null;
        }

        String raw = matcher.group(2);
        logger.info("raw: {}", raw);

        DecodedData decodedData = JsonbBuilder.create().fromJson(raw, DecodedData.class);
        logger.info(decodedData.toString());

        Request request = requestBuilder.buildRequestForOrder(token, decodedData.getApplicationId());

        return httpExcecutor.execute(OrderResponse.class, client, request);
    }
}
