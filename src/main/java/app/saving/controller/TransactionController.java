package app.saving.controller;

import app.saving.controller.dto.TransactionPutRequest;
import app.saving.facade.TransactionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/accounts/{accountId}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionFacade transactionFacade;

    @PutMapping("/{transactionId}")
    public void addCategory(@PathVariable UUID transactionId, @RequestBody TransactionPutRequest transactionPutRequest){
        transactionFacade.updateCategory(transactionId, transactionPutRequest.category());
    }
}
