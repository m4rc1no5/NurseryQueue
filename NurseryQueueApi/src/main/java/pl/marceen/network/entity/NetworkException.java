package pl.marceen.network.entity;

import org.slf4j.Logger;

/**
 * @author Marcin Zaremba
 */
public class NetworkException extends Exception {

    private static final String MSG_CONNECTION_PROBLEM = "Connection problem";
    private static final String NO_ANSWER = "No answer";

    public static NetworkException connectionProblem(String details, Logger logger) {
        logger.error(String.format("%s - %s", MSG_CONNECTION_PROBLEM, details));

        return new NetworkException(MSG_CONNECTION_PROBLEM);
    }

    public static NetworkException noAnswer(Logger logger) {
        logger.error("Response is empty");

        return new NetworkException(NO_ANSWER);
    }

    private NetworkException(String message) {
        super(message);
    }
}
