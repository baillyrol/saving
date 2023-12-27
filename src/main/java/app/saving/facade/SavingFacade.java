package app.saving.facade;

import app.saving.controller.dto.CashFlowDetailsDto;
import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import app.saving.entity.ConnectionEntity;
import app.saving.entity.TransactionEntity;
import app.saving.facade.mapper.AccountMapper;
import app.saving.facade.mapper.TransactionMapper;
import app.saving.gateway.PowensGateway;
import app.saving.repository.AccountRepository;
import app.saving.repository.CashFlowRepository;
import app.saving.repository.ConnectionRepository;
import app.saving.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavingFacade {

    private final PowensGateway powensGateway;
    private final TransactionRepository transactionRepository;
    private final CashFlowRepository cashFlowRepository;
    private final AccountRepository accountRepository;
    private final ConnectionRepository connectionRepository;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;
    @Value("${mock.userId}")
    private UUID user;

    public List<UUID> syncAccounts() {
        List<AccountEntity> accountsCreated = connectionRepository.findByUserId(user).stream()
                .flatMap(connectionEntity ->
                        powensGateway.getAccounts(connectionEntity.getToken()).accounts().stream()
                                .map(account -> accountMapper.from(account, connectionEntity.getId()))
                )
                .filter(accountEntity -> !accountRepository.existsByExternalAccountId(accountEntity.getExternalAccountId()))
                .toList();
        return accountRepository.saveAll(accountsCreated).stream().map(AccountEntity::getId).toList();
    }

    public void syncTransactions(UUID accountId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("JDD"));
        ConnectionEntity connectionEntity = connectionRepository.findById(accountEntity.getConnectionId()).orElseThrow(() -> new RuntimeException("JDD"));
        List<TransactionEntity> transactionEntities = powensGateway.getTransactions(accountEntity.getExternalAccountId(), connectionEntity.getToken())
                .transactions()
                .stream()
                .map(transaction -> transactionMapper.from(transaction, accountId))
                .filter(transactionEntity -> !transactionRepository.existsByExternalId(transactionEntity.getExternalId()))
                .toList();

        transactionRepository.saveAll(transactionEntities);
    }


    public void syncCashFlow(UUID accountId, int year, int month) {
        cashFlowRepository.findByAccountIdAndYearAndMonth(accountId, year, month)
                .ifPresent(cashFlowRepository::delete);

        LocalDate fromDate = LocalDate.of(year, month, 1);
        LocalDate endDate = LocalDate.of(year, month, fromDate.lengthOfMonth());
        List<TransactionEntity> transactionEntities = this.transactionRepository
                .findAllByAccountIdAndDateBetweenOrderByDate(accountId, fromDate, endDate);

        double income = transactionEntities.stream()
                .map(TransactionEntity::getVal)
                .filter(value -> value > 0)
                .mapToDouble(f -> f)
                .sum();

        double expense = transactionEntities.stream()
                .map(TransactionEntity::getVal)
                .filter(value -> value < 0)
                .mapToDouble(f -> f)
                .sum();

        CashFlowEntity cashFlowEntity = new CashFlowEntity()
                .setId(UUID.randomUUID())
                .setFirstDate(fromDate)
                .setLastDate(endDate)
                .setAccountId(accountId)
                .setMonth(month)
                .setYear(year)
                .setIncome(Math.round(income * 100.0) / 100.0)
                .setExpense( Math.round(expense * 100.0) / 100.0)
                .setCashFlow(Math.round((income + expense) * 100.0) / 100.0)
                .setTotalTransaction(transactionEntities.size());

        cashFlowRepository.save(cashFlowEntity);
    }

    public List<AccountEntity> getAccountEntity() {
        return accountRepository.findAll();
    }

    public List<CashFlowEntity> getCashFlows(UUID accountId) {
        return cashFlowRepository.findAllByAccountId(accountId);
    }

    public CashFlowDetailsDto getCashFlowDetails(UUID accountId, Integer month) {

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        CashFlowEntity accountIdAndMonth = cashFlowRepository.findByAccountIdAndMonth(accountId, month);
        LocalDate fromDate = LocalDate.of(2023, month, 1);
        LocalDate endDate = LocalDate.of(2023, month, fromDate.lengthOfMonth());

        List<TransactionEntity> transactionEntities = this.transactionRepository
                .findAllByAccountIdAndDateBetweenOrderByDate(accountId, fromDate, endDate);

        return new CashFlowDetailsDto(
                accountEntity,
                accountIdAndMonth,
                transactionEntities.stream().filter(transactionEntity -> transactionEntity.getVal() > 0).toList(),
                transactionEntities.stream().filter(transactionEntity -> transactionEntity.getVal() < 0).toList()
        );
    }
}
