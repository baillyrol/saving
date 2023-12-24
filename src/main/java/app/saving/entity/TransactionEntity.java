package app.saving.entity;

import app.saving.gateway.dto.Transaction;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    private Long id;
    private Long accountId;
    private LocalDate date;
    private String originalWording;
    private Double value;
    private String formattedValue;

    public TransactionEntity() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public TransactionEntity(Long id, Long accountId, LocalDate date, String originalWording, Double value, String formattedValue) {
        this.id = id;
        this.accountId = accountId;
        this.date = date;
        this.originalWording = originalWording;
        this.value = value;
        this.formattedValue = formattedValue;
    }

    public static TransactionEntity create(Transaction transaction) {
        return new TransactionEntity(
                Long.valueOf(transaction.id()),
                Long.valueOf(transaction.idAccount()),
                LocalDate.parse(transaction.date()),
                transaction.originalWording(),
                transaction.value(),
                transaction.formatted_value()
        );
    }

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getOriginalWording() {
        return originalWording;
    }

    public void setOriginalWording(String originalWording) {
        this.originalWording = originalWording;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getFormattedValue() {
        return formattedValue;
    }

    public void setFormattedValue(String formattedValue) {
        this.formattedValue = formattedValue;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionEntity that = (TransactionEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        return Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        return result;
    }
}
