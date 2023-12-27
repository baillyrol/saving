package app.saving.repository;


import app.saving.entity.CashFlowEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CashFlowRepository extends JpaRepository<CashFlowEntity, UUID> {
    List<CashFlowEntity> findAllByAccountId(UUID accountId);
    CashFlowEntity findByAccountIdAndMonth(UUID accountId, Integer month);

    Optional<CashFlowEntity> findByAccountIdAndYearAndMonth(UUID accountId, Integer month, Integer year);
}
