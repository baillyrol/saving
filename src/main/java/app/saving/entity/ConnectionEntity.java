package app.saving.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "CONNECTION")
@Data
public class ConnectionEntity {
    @Id
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;
    @Column(name = "external_connection_id")
    private String externalConnectionId;
    @Column
    private String token;
    @Column(name = "user_id")
    private UUID userId;
}
