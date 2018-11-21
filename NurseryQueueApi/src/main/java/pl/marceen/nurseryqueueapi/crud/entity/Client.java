package pl.marceen.nurseryqueueapi.crud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Marcin Zaremba
 */
@Entity
@NamedQuery(
        name = Client.FIND_ALL_ACTIVE_BY_SERVICE,
        query = "SELECT c FROM Client c WHERE c.active = TRUE AND c.service = :service"
)
public class Client extends SimpleEntity {

    public static final String FIND_ALL_ACTIVE_BY_SERVICE = "Client.findAllActiveByService";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientIdGenerator")
    @SequenceGenerator(name = "ClientIdGenerator", sequenceName = "client_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private Boolean active;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Service service;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private String emails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

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

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", active=").append(active);
        sb.append(", service=").append(service);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", emails='").append(emails).append('\'');
        sb.append('}');
        sb.append(super.toString());
        return sb.toString();
    }
}
