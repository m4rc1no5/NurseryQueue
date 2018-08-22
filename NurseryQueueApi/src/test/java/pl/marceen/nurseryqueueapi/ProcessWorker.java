package pl.marceen.nurseryqueueapi;

import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.DecodedData;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginRequest;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginResponse;
import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.Page;

import javax.json.bind.JsonbBuilder;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marcin Zaremba
 */
public class ProcessWorker {
    private static final Logger logger = LoggerFactory.getLogger(ProcessWorker.class);

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final String LOGIN = "";
    private static final String PASSWORD = "";

    @Test
    @Ignore
    public void process() throws IOException {
        logger.info("Process START");

        OkHttpClient client = new OkHttpClient();

        logger.info("Login");
        LoginRequest LoginRequestPayload = new LoginRequest();
        LoginRequestPayload.setLogin(LOGIN);
        LoginRequestPayload.setPassword(PASSWORD);

        String json = JsonbBuilder.create().toJson(LoginRequestPayload);
        logger.info("LoginRequest payload: {}", json);

        Request request = new Request.Builder()
                .url(Page.LOGIN.getUrl())
                .post(RequestBody.create(JSON, json))
                .build();

        Response response = client.newCall(request).execute();

        String responseAsString = response.body().string();
        logger.info("Response: {}", responseAsString);

        LoginResponse loginResponse = JsonbBuilder.create().fromJson(responseAsString, LoginResponse.class);

        String token = loginResponse.getToken();

        Base64 base64 = new Base64();
        byte[] bytes = base64.decode(token);
        String decodedDataAsString = new String(bytes, StandardCharsets.UTF_8);
        logger.info("Decoded base64: {}", decodedDataAsString);

        Pattern pattern = Pattern.compile("^(\\{\"alg\":\"HS256\",\"typ\":\"JWS\"})(\\{.*})");
        Matcher matcher = pattern.matcher(decodedDataAsString);

        if (!matcher.find()) {
            logger.error("Problem with decoding base64");
            return;
        }

        String raw = matcher.group(2);
        logger.info("raw: {}", raw);

        DecodedData decodedData = JsonbBuilder.create().fromJson(raw, DecodedData.class);
        logger.info(decodedData.toString());

        logger.info("Check login");
        Request requestCheckLogin = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(Page.DICTIONARY.getUrl())
                .build();
        Response responseCheckLogin = client.newCall(requestCheckLogin).execute();
        logger.info("Response CheckLogin: {}", responseCheckLogin.body().string());

        Request requestCheckLogin2 = new Request.Builder()
                .addHeader("Authorization", "Bearer " + token)
                .url(Page.ORDER.getUrl() + decodedData.getApplicationId())
                .build();
        Response responseCheckLogin2 = client.newCall(requestCheckLogin2).execute();
        logger.info("Response CheckLogin2: {}", responseCheckLogin2.body().string());

        logger.info("Process STOP");
    }
}
