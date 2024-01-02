package app.saving.facade;

import app.saving.controller.dto.CashFlowDetailsDto;
import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import app.saving.entity.TransactionEntity;
import app.saving.repository.AccountRepository;
import app.saving.repository.CashFlowRepository;
import app.saving.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SavingFacade {

    private final TransactionRepository transactionRepository;
    private final CashFlowRepository cashFlowRepository;
    private final AccountRepository accountRepository;

    public List<AccountEntity> getAccountEntity() {
        return accountRepository.findAll();
    }

    public List<CashFlowEntity> getCashFlows(UUID accountId) {
        return cashFlowRepository.findAllByAccountId(accountId);
    }

    public CashFlowDetailsDto getCashFlowDetails(UUID accountId, Integer month) {

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(IllegalArgumentException::new);
        CashFlowEntity accountIdAndMonth = cashFlowRepository.findByAccountIdAndMonth(accountId, month);
        LocalDate fromDate = LocalDate.of(2024, month, 1);
        LocalDate endDate = LocalDate.of(2024, month, fromDate.lengthOfMonth());

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
