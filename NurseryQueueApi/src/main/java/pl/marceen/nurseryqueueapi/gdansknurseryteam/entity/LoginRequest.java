package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

import javax.json.bind.annotation.JsonbProperty;

/**
 * @author Marcin Zaremba
 */
public class LoginRequest {

    @JsonbProperty("uzytkownik_nazwa")
    private String login;

    @JsonbProperty("haslo")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginRequest{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
