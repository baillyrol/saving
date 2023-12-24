package app.saving.controller;

import app.saving.SavingFacade;
import app.saving.controller.dto.CashFlowDetailsDto;
import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class SavingController {

    private final SavingFacade savingFacade;

    @Autowired
    public SavingController(SavingFacade savingFacade) {
        this.savingFacade = savingFacade;
    }

    @PostMapping("/accounts/{connectionId}/sync")
    public void syncAccounts(@PathVariable Long connectionId) {
        savingFacade.syncAccounts(connectionId);
    }

    @PostMapping("/accounts/{accountId}/transactions/sync")
    public void syncTransactions(@PathVariable Long accountId) {
        savingFacade.syncTransactions(accountId);
    }

    @PostMapping("/accounts/{accountId}/cashFlow/sync")
    public void syncCashFlow(@PathVariable Long accountId) {
        savingFacade.syncCashFlow(accountId, 2023, 10);
        savingFacade.syncCashFlow(accountId, 2023, 11);
        savingFacade.syncCashFlow(accountId,2023, 12);
    }

    @GetMapping("/accounts")
    private List<AccountEntity> getAccounts() {
        return savingFacade.getAccountEntity();
    }

    @GetMapping("/accounts/{accountId}/cashFlows")
    public List<CashFlowEntity> getCashFlows(@PathVariable Long accountId) {
        return savingFacade.getCashFlows(accountId);
    }

    @GetMapping("/accounts/{accountId}/cashFlows/{month}/details")
    private CashFlowDetailsDto getCashFlowDetails(@PathVariable Long accountId, @PathVariable Integer month) {
        return savingFacade.getCashFlowDetails(accountId, month);
    }

}
