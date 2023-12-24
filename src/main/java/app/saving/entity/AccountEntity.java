package app.saving.entity;

import app.saving.gateway.dto.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "ACCOUNT")
public class AccountEntity {

    @Id
    private Long id;
    private String idConnection;
    private String idUser;
    private String iban;
    private String name;
    private Double comingBalance;
    private String formattedBalance;

    @CreationTimestamp
    private Instant createdOn;
    @UpdateTimestamp
    private Instant lastUpdatedOn;


    public AccountEntity() {
        // Constructeur par défaut nécessaire pour JPA
    }

    public AccountEntity(Long id, String idConnection, String idUser, String iban, String name, Double comingBalance, String formattedBalance) {
        this.id = id;
        this.idConnection = idConnection;
        this.idUser = idUser;
        this.iban = iban;
        this.name = name;
        this.comingBalance = comingBalance;
        this.formattedBalance = formattedBalance;
    }

    public static AccountEntity create(Account account) {
        return new AccountEntity(account.id(), account.idConnection(), account.idUser(), account.iban(), account.name(), account.comingBalance(), account.formattedBalance());
    }

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdConnection() {
        return idConnection;
    }

    public void setIdConnection(String idConnection) {
        this.idConnection = idConnection;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Double getComingBalance() {
        return comingBalance;
    }

    public void setComingBalance(Double comingBalance) {
        this.comingBalance = comingBalance;
    }

    public String getFormattedBalance() {
        return formattedBalance;
    }

    public void setFormattedBalance(String formattedBalance) {
        this.formattedBalance = formattedBalance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Instant lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountEntity that = (AccountEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(idConnection, that.idConnection)) return false;
        if (!Objects.equals(idUser, that.idUser)) return false;
        if (!Objects.equals(iban, that.iban)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(comingBalance, that.comingBalance))
            return false;
        return  (!Objects.equals(formattedBalance, that.formattedBalance));
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (idConnection != null ? idConnection.hashCode() : 0);
        result = 31 * result + (idUser != null ? idUser.hashCode() : 0);
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comingBalance != null ? comingBalance.hashCode() : 0);
        result = 31 * result + (formattedBalance != null ? formattedBalance.hashCode() : 0);
        return result;
    }
}
