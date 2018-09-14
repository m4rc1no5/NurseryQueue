package pl.marceen.nurseryqueueapi.crud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Marcin Zaremba
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = Client.FIND_ALL_ACTIVE_BY_SERVICE,
                query = "SELECT c FROM Client c WHERE c.active = TRUE AND c.service = :service"
        )
})
public class Client {

    public static final String FIND_ALL_ACTIVE_BY_SERVICE = "Client.findAllActiveByService";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientIdGenerator")
    @SequenceGenerator(name = "ClientIdGenerator", sequenceName = "client_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Version
    private LocalDateTime updatedAt;

    @NotNull
    private Boolean active;

    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Service service;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @PrePersist
    public void setUp() {
        createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Client{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", active=").append(active);
        sb.append(", service=").append(service);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append("...").append('\'');
        sb.append('}');
        return sb.toString();
    }
}
