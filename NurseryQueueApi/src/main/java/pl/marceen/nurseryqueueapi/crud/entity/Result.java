package pl.marceen.nurseryqueueapi.crud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Marcin Zaremba
 */
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ResultIdGenerator")
    @SequenceGenerator(name = "ResultIdGenerator", sequenceName = "result_id_seq", allocationSize = 1)
    private Long id;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Version
    private LocalDateTime updatedAt;

    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "idclient")
    private Client client;

    private String firstNurseryName;
    private Integer firstNurseryStanding;

    private String secondNurseryName;
    private Integer secondNurseryStanding;

    private String thirdNurseryName;
    private Integer thirdNurseryStanding;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getFirstNurseryName() {
        return firstNurseryName;
    }

    public void setFirstNurseryName(String firstNurseryName) {
        this.firstNurseryName = firstNurseryName;
    }

    public Integer getFirstNurseryStanding() {
        return firstNurseryStanding;
    }

    public void setFirstNurseryStanding(Integer firstNurseryStanding) {
        this.firstNurseryStanding = firstNurseryStanding;
    }

    public String getSecondNurseryName() {
        return secondNurseryName;
    }

    public void setSecondNurseryName(String secondNurseryName) {
        this.secondNurseryName = secondNurseryName;
    }

    public Integer getSecondNurseryStanding() {
        return secondNurseryStanding;
    }

    public void setSecondNurseryStanding(Integer secondNurseryStanding) {
        this.secondNurseryStanding = secondNurseryStanding;
    }

    public String getThirdNurseryName() {
        return thirdNurseryName;
    }

    public void setThirdNurseryName(String thirdNurseryName) {
        this.thirdNurseryName = thirdNurseryName;
    }

    public Integer getThirdNurseryStanding() {
        return thirdNurseryStanding;
    }

    public void setThirdNurseryStanding(Integer thirdNurseryStanding) {
        this.thirdNurseryStanding = thirdNurseryStanding;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Result{");
        sb.append("id=").append(id);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", client=").append(client);
        sb.append(", firstNurseryName='").append(firstNurseryName).append('\'');
        sb.append(", firstNurseryStanding=").append(firstNurseryStanding);
        sb.append(", secondNurseryName='").append(secondNurseryName).append('\'');
        sb.append(", secondNurseryStanding=").append(secondNurseryStanding);
        sb.append(", thirdNurseryName='").append(thirdNurseryName).append('\'');
        sb.append(", thirdNurseryStanding=").append(thirdNurseryStanding);
        sb.append('}');
        return sb.toString();
    }
}
