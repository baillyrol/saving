package app.saving.repository;


import app.saving.entity.CashFlowEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CashFlowRepository extends JpaRepository<CashFlowEntity, Long> {
    List<CashFlowEntity> findAllByAccountId(Long accountId);
    CashFlowEntity findByAccountIdAndMonth(Long accountId, Integer month);

    Optional<CashFlowEntity> findByAccountIdAndYearAndMonth(Long accountId, Integer month, Integer year);
}
