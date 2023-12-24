package app.saving.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "CASH_FLOW")
public class CashFlowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "first_date")
    private LocalDate firstDate;

    @Column(name = "last_date")
    private LocalDate lastDate;

    @Column(name = "month")
    private Integer month;

    @Column(name = "year")
    private Integer year;

    @Column(name = "income")
    private Double income;

    @Column(name = "expense")
    private Double expense;

    @Column(name = "cash_flow")
    private Double cashFlow;

    @Column(name = "total_transaction")
    int totalTransaction;

    public CashFlowEntity() {

    }

    public CashFlowEntity(LocalDate firstDate, LocalDate lastDate, Long accountId, Integer month, Integer year, Double income, Double expense, Double cashFlow, int totalTransaction) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(LocalDate firstDate) {
        this.firstDate = firstDate;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getExpense() {
        return expense;
    }

    public void setExpense(Double expense) {
        this.expense = expense;
    }

    public Double getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(Double cashFlow) {
        this.cashFlow = cashFlow;
    }

    public int getTotalTransaction() {
        return totalTransaction;
    }

    public void setTotalTransaction(int totalTransaction) {
        this.totalTransaction = totalTransaction;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashFlowEntity that = (CashFlowEntity) o;

        if (totalTransaction != that.totalTransaction) return false;
        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(accountId, that.accountId)) return false;
        if (!Objects.equals(firstDate, that.firstDate)) return false;
        if (!Objects.equals(lastDate, that.lastDate)) return false;
        if (!Objects.equals(month, that.month)) return false;
        if (!Objects.equals(year, that.year)) return false;
        if (!Objects.equals(income, that.income)) return false;
        if (!Objects.equals(expense, that.expense)) return false;
        return Objects.equals(cashFlow, that.cashFlow);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        result = 31 * result + (firstDate != null ? firstDate.hashCode() : 0);
        result = 31 * result + (lastDate != null ? lastDate.hashCode() : 0);
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (income != null ? income.hashCode() : 0);
        result = 31 * result + (expense != null ? expense.hashCode() : 0);
        result = 31 * result + (cashFlow != null ? cashFlow.hashCode() : 0);
        result = 31 * result + totalTransaction;
        return result;
    }
}
