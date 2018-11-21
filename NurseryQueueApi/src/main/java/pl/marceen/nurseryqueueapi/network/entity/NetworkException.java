package pl.marceen.nurseryqueueapi.network.entity;

import org.slf4j.Logger;

/**
 * @author Marcin Zaremba
 */
public class NetworkException extends Exception {

    private static final String MSG_CONNECTION_PROBLEM = "Connection problem";

    public static NetworkException connectionProblem(String details, Logger logger) {
        logger.error("{} - details: {}", MSG_CONNECTION_PROBLEM, details);

        return new NetworkException(MSG_CONNECTION_PROBLEM);
    }

    private NetworkException(String message) {
        super(message);
    }
}
