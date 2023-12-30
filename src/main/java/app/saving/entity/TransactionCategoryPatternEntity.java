package app.saving.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "TRANSACTION_CATEGORY_PATTERN")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCategoryPatternEntity {

    @Id
    private UUID id;
    private String ibanPattern;
    private String originalWordingPattern;
    private UUID categoryId;
}
