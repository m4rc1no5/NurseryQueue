package pl.marceen.nurseryqueueapi.crud.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Marcin Zaremba
 */
@MappedSuperclass
class SimpleEntity {

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Version
    private LocalDateTime updatedAt;

    private String description;

    @PrePersist
    public void setUp() {
        createdAt = LocalDateTime.now();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SimpleEntity{");
        sb.append("createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", description='").append(description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
