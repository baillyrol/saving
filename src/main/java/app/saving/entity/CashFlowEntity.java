package app.saving.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "CASH_FLOW")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashFlowEntity {
    @Id
    @Column(columnDefinition = "UUID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "first_date")
    private LocalDate firstDate;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Column(name = "cashflow_month")
    private Integer month;

    @Column(name = "cashflow_year")
    private Integer year;

    @Column(name = "income")
    private Double income;

    @Column(name = "expense")
    private Double expense;

    @Column(name = "cash_flow")
    private Double cashFlow;

    @Column(name = "total_transaction")
    int totalTransaction;




    public CashFlowEntity(LocalDate firstDate, LocalDate lastDate, UUID accountId, Integer month, Integer year, Double income, Double expense, Double cashFlow, int totalTransaction) {
        this.id = UUID.randomUUID();
        this.firstDate = firstDate;
        this.lastDate = lastDate;
        this.accountId = accountId;
        this.month = month;
        this.year = year;
        this.income = income;
        this.expense = expense;
        this.cashFlow = cashFlow;
        this.totalTransaction = totalTransaction;
    }


}
