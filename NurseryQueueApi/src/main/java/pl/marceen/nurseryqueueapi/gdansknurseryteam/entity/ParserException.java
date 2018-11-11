package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import org.slf4j.Logger;

/**
 * @author Marcin Zaremba
 */
public class ParserException extends Exception {

    private static final String DECODED_DATA_NOT_FOUND = "Decoded data not found";
    private static final String PROBLEM_WITH_BUILDING_JSONB = "Problem with building Jsonb";

    public static ParserException decodedDataNotFound(Logger logger) throws ParserException {
        logger.error(DECODED_DATA_NOT_FOUND);

        throw new ParserException(DECODED_DATA_NOT_FOUND);
    }

    public static ParserException problemWithBuildingJsonb(String details, Logger logger) throws ParserException {
        logger.error(String.format("%s - %s", PROBLEM_WITH_BUILDING_JSONB, details));

        throw new ParserException(PROBLEM_WITH_BUILDING_JSONB);
    }

    private ParserException(String message) {
        super(message);
    }
}
