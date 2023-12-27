package app.saving.repository;


import app.saving.entity.AccountEntity;
import app.saving.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConnectionRepository extends JpaRepository<ConnectionEntity, UUID> {
    List<ConnectionEntity> findByUserId(UUID userId);
}
