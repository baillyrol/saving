package app.saving.facade;

import app.saving.entity.ConnectionEntity;
import app.saving.gateway.PowensGateway;
import app.saving.gateway.dto.response.GetTokenResponse;
import app.saving.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConnectionFacade {

    private final PowensGateway powensGateway;
    private final ConnectionRepository connectionRepository;
    private final SavingFacade savingFacade;

    public void add(String code, String connectionId, String userId) {
        GetTokenResponse token = powensGateway.getToken(code);
        ConnectionEntity connectionEntity = new ConnectionEntity()
                .setId(UUID.randomUUID())
                .setExternalConnectionId(connectionId)
                .setUserId(UUID.fromString(userId))
                .setToken(token.token());
        connectionRepository.save(connectionEntity);
        List<UUID> uuids = savingFacade.syncAccounts();
        uuids.forEach(savingFacade::syncTransactions);
        uuids.forEach(accountId -> {
            savingFacade.syncCashFlow(accountId, 2023, 10);
            savingFacade.syncCashFlow(accountId, 2023, 11);
            savingFacade.syncCashFlow(accountId, 2023, 12);
        });
    }
}