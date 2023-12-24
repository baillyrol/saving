package app.saving;

import app.saving.controller.dto.CashFlowDetailsDto;
import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import app.saving.entity.TransactionEntity;
import app.saving.gateway.PowensGateway;
import app.saving.repository.AccountEntityRepository;
import app.saving.repository.CashFlowRepository;
import app.saving.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SavingFacade {

    private final PowensGateway powensGateway;
    private final TransactionRepository transactionRepository;
    private final CashFlowRepository cashFlowRepository;
    private final AccountEntityRepository accountEntityRepository;

    public SavingFacade(PowensGateway powensGateway, TransactionRepository transactionRepository, CashFlowRepository cashFlowRepository, AccountEntityRepository accountEntityRepository) {
        this.powensGateway = powensGateway;
        this.transactionRepository = transactionRepository;
        this.cashFlowRepository = cashFlowRepository;
        this.accountEntityRepository = accountEntityRepository;
    }

    public void syncAccounts(Long connectionId) {
        List<AccountEntity> list = powensGateway.getAccounts(connectionId)
                .accounts()
                .stream()
                .map(AccountEntity::create)
                .toList();

        accountEntityRepository.saveAll(list);
    }

    public void syncTransactions(Long accountId) {
        List<TransactionEntity> transactionEntities = powensGateway.getTransactions(accountId)
                .transactions()
                .stream()
                .map(TransactionEntity::create)
                .toList();

        transactionRepository.saveAll(transactionEntities);
    }

    public void syncCashFlow(Long accountId, int year, int month) {
        this.cashFlowRepository.findByAccountIdAndYearAndMonth(accountId, year, month)
                .ifPresent(cashFlowRepository::delete);

        LocalDate fromDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, fromDate.lengthOfMonth());
        List<TransactionEntity> transactionEntities = this.transactionRepository
                .findAllByAccountIdAndDateBetweenOrderByDate(accountId, fromDate, endDate);

        double income = transactionEntities.stream()
                .map(TransactionEntity::getValue)
                .filter(value -> value > 0)
                .mapToDouble(f -> f)
                .sum();

        double expense = transactionEntities.stream()
                .map(TransactionEntity::getValue)
                .filter(value -> value < 0)
                .mapToDouble(f -> f)
                .sum();

        CashFlowEntity cashFlowEntity = new CashFlowEntity(
                fromDate,
                endDate,
                accountId,
                month,
                year,
                Math.round(income * 100.0) / 100.0,
                Math.round(expense * 100.0) / 100.0,
                Math.round((income + expense) * 100.0) / 100.0,
                transactionEntities.size());

        cashFlowRepository.save(cashFlowEntity);
    }

    public List<AccountEntity> getAccountEntity() {
        return accountEntityRepository.findAll();
    }

    public List<CashFlowEntity> getCashFlows(Long accountId) {
        return cashFlowRepository.findAllByAccountId(accountId);
    }

    public CashFlowDetailsDto getCashFlowDetails(Long accountId, Integer month) {

        AccountEntity accountEntity = accountEntityRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        CashFlowEntity accountIdAndMonth = cashFlowRepository.findByAccountIdAndMonth(accountId, month);
        LocalDate fromDate = LocalDate.of(2023, month, 1);
        LocalDate endDate = LocalDate.of(2023, month, fromDate.lengthOfMonth());

        List<TransactionEntity> transactionEntities = this.transactionRepository
                .findAllByAccountIdAndDateBetweenOrderByDate(accountId, fromDate, endDate);

        return new CashFlowDetailsDto(
                accountEntity,
                accountIdAndMonth,
                transactionEntities.stream().filter(transactionEntity -> transactionEntity.getValue() > 0).toList(),
                transactionEntities.stream().filter(transactionEntity -> transactionEntity.getValue() < 0).toList()
        );
    }
}
