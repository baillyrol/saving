package app.saving.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "TRANSACTION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEntity {

    @Id
    private UUID id;
    private String externalId;
    private UUID accountId;
    private LocalDate date;
    private String originalWording;
    private Double val;
    private String formattedValue;
    private String clientIban;
    private String clientName;
    @OneToOne
    @JoinColumn(name = "category_id", nullable = true)
    private TransactionCategoryEntity category;
}
