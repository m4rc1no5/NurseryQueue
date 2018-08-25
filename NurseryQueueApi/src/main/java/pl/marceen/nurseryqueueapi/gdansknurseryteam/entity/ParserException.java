package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import org.slf4j.Logger;

/**
 * @author Marcin Zaremba
 */
public class ParserException extends Exception {

    private static final String DECODED_DATA_NOT_FOUND = "Decoded data not found";

    public static ParserException decodedDataNotFound(Logger logger) throws ParserException {
        logger.error(DECODED_DATA_NOT_FOUND);

        throw new ParserException(DECODED_DATA_NOT_FOUND);
    }

    private ParserException(String message) {
        super(message);
    }
}
