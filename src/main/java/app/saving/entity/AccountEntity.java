package app.saving.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "ACCOUNT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

    @Id
    private UUID id;
    @Column(name = "connection_id")
    private UUID connectionId;
    @Column(name = "external_connection_id")
    private String externalConnectionId;
    @Column(name = "external_account_id")
    private String externalAccountId;
    private String iban;
    private String name;
    private String token;
    @Column(name = "coming_balance")
    private Double comingBalance;
    @Column(name = "formatted_balance")
    private String formattedBalance;

    @CreationTimestamp
    @Column(name = "created_on")
    private Instant createdOn;
    @UpdateTimestamp
    @Column(name = "last_updated_on")
    private Instant lastUpdatedOn;


}
