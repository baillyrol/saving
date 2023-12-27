package app.saving.controller;

import app.saving.facade.SavingFacade;
import app.saving.facade.mapper.SynchronizationFacade;
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
    private final SynchronizationFacade synchronizationFacade;

    @PostMapping("/sync")
    public void syncAccounts() {
        synchronizationFacade.syncAll();
    }
}
