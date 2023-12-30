package app.saving.controller;

import app.saving.facade.SavingFacade;
import app.saving.controller.dto.CashFlowDetailsDto;
import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SavingController {

    private final SavingFacade savingFacade;

    @GetMapping("/accounts")
    private List<AccountEntity> getAccounts() {
        return savingFacade.getAccountEntity();
    }

    @GetMapping("/accounts/{accountId}/cashFlows")
    public List<CashFlowEntity> getCashFlows(@PathVariable UUID accountId) {
        return savingFacade.getCashFlows(accountId);
    }

    @GetMapping("/accounts/{accountId}/cashFlows/{month}/details")
    public CashFlowDetailsDto getCashFlowDetails(@PathVariable UUID accountId, @PathVariable Integer month) {
        return savingFacade.getCashFlowDetails(accountId, month);
    }

}
