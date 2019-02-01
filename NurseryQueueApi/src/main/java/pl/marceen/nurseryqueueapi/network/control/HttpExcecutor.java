package pl.marceen.nurseryqueueapi.network.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.network.entity.NetworkException;

import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutionException;

/**
 * @author Marcin Zaremba
 */
public class HttpExcecutor<T> {
    private static final Logger logger = LoggerFactory.getLogger(HttpExcecutor.class);

    public T execute(Class<T> clazz, HttpClient client, HttpRequest request) throws NetworkException, InterruptedException {
        logger.info("START");

        var response = getResponse(client, request);
        logger.info("Raw response: {}", response);

        logger.info("Try to convert response to object class {}", clazz.getSimpleName());
        T result = getResult(response, clazz);

        logger.info("STOP");

        return result;
    }

    private T getResult(String response, Class<T> clazz) throws NetworkException {
        try (var jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(response, clazz);
        } catch (Exception e) {
            throw NetworkException.connectionProblem(e.getMessage(), logger);
        }
    }

    private String getResponse(HttpClient client, HttpRequest request) throws NetworkException, InterruptedException {
        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString())
                    .body();
        } catch (IOException e) {
            throw NetworkException.connectionProblem(e.getMessage(), logger);
        }
    }
}
