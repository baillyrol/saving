package app.saving.controller;

import app.saving.facade.SavingFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class SynchronizationController {
    private final SavingFacade savingFacade;

    @PostMapping("/accounts/sync")
    public void syncAccounts() {
        savingFacade.syncAccounts();
    }

    @PostMapping("/accounts/{accountId}/transactions/sync")
    public void syncTransactions(@PathVariable UUID accountId) {
        savingFacade.syncTransactions(accountId);
    }

    @PostMapping("/accounts/{accountId}/cashFlow/sync")
    public void syncCashFlow(@PathVariable UUID accountId) {
        savingFacade.syncCashFlow(accountId, 2023, 10);
        savingFacade.syncCashFlow(accountId, 2023, 11);
        savingFacade.syncCashFlow(accountId,2023, 12);
    }
}
