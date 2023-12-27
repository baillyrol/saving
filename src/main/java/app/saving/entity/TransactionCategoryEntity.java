package app.saving.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TRANSACTION_CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryEntity {
    @Id
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;
    private String name;
    private UUID categorySup;
}
