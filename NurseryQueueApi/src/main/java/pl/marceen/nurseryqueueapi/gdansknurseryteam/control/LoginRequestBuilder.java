package pl.marceen.nurseryqueueapi.gdansknurseryteam.control;

import pl.marceen.nurseryqueueapi.gdansknurseryteam.entity.LoginRequest;

/**
 * @author Marcin Zaremba
 */
public class LoginRequestBuilder {
    public LoginRequest build(String login, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setLogin(login);
        loginRequest.setPassword(password);

        return loginRequest;
    }
}
