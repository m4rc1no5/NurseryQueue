package pl.marceen.nurseryqueueapi.network.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.json.bind.JsonbBuilder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author Marcin Zaremba
 */
public class HttpExcecutor<T> {
    private static final Logger logger = LoggerFactory.getLogger(HttpExcecutor.class);

    public T execute(Class<T> clazz, HttpClient client, HttpRequest request) throws NetworkException {
        logger.info("START");

        var response = getResponse(client, request);
        logger.info("Raw response: {}", response);

        logger.info("Try to convert response to object class {}", clazz.getSimpleName());
        T result = JsonbBuilder.create().fromJson(response, clazz);
        logger.info(result.toString());

        logger.info("STOP");

        return result;
    }

    private String getResponse(HttpClient client, HttpRequest request) throws NetworkException {
        try {
            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            throw NetworkException.connectionProblem(e.getMessage(), logger);
        }
    }
}
