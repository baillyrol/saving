package app.saving.repository;


import app.saving.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findAllByAccountIdAndDateBetweenOrderByDate(UUID accountId, LocalDate dateFrom, LocalDate dateTo);
    boolean existsByExternalId(String externalId);

}
