package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DecodedData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.ParserException;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import java.util.regex.Pattern;

/**
 * @author Marcin Zaremba
 */
public class TokenDecoder {
    private static final Logger logger = LoggerFactory.getLogger(TokenDecoder.class);

    private static final Pattern PATTERN = Pattern.compile("^(\\{\"alg\":\"HS256\",\"typ\":\"JWS\"})(\\{.*})");

    @Inject
    private Base64Decoder base64Decoder;

    DecodedData decode(String token) throws ParserException {
        logger.info("Decoding data from token: {}", token);

        var matcher = PATTERN.matcher(base64Decoder.decode(token));

        if (!matcher.find()) {
            throw ParserException.decodedDataNotFound(logger);
        }

        var decodedDataAsJson = matcher.group(2);
        logger.info("Decoded data as Json: {}", decodedDataAsJson);

        var decodedData = convertToDecodedData(decodedDataAsJson);
        logger.info(decodedData.toString());

        return decodedData;
    }

    private DecodedData convertToDecodedData(String json) throws ParserException {
        try (var jsonb = JsonbBuilder.create()) {
            return jsonb.fromJson(json, DecodedData.class);
        } catch (JsonbException e) {
            throw ParserException.decodedDataNotFound(logger);
        } catch (Exception e) {
            throw ParserException.problemWithBuildingJsonb(e.getMessage(), logger);
        }
    }
}
