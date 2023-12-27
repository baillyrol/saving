package app.saving.repository;


import app.saving.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    boolean existsByExternalAccountId(String externalAccountId);
}

