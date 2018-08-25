package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author Marcin Zaremba
 */
public class Base64Decoder {
    private static final Logger logger = LoggerFactory.getLogger(Base64Decoder.class);

    public String decode(String value) {
        logger.info("Try to decode value: {}", value);

        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(value);
        String decodedValue = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Decoded value: {}", decodedValue);

        return decodedValue;
    }
}
