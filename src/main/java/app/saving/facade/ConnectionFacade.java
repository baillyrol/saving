package app.saving.facade;

import app.saving.entity.ConnectionEntity;
import app.saving.gateway.PowensGateway;
import app.saving.gateway.dto.response.GetTokenResponse;
import app.saving.repository.ConnectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConnectionFacade {

    private final PowensGateway powensGateway;
    private final ConnectionRepository connectionRepository;
    private final SynchronizationFacade synchronizationFacade;


    @SneakyThrows
    @Transactional
    public void add(String code, String connectionId, String userId) {
        GetTokenResponse token = powensGateway.getToken(code);
        ConnectionEntity connectionEntity = new ConnectionEntity()
                .setId(UUID.randomUUID())
                .setExternalConnectionId(connectionId)
                .setUserId(UUID.fromString(userId))
                .setToken(token.token());
        connectionRepository.save(connectionEntity);
        Thread.sleep(10000);
        synchronizationFacade.syncAll();
    }
}
