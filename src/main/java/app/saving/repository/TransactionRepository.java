package app.saving.repository;


import app.saving.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findAllByDateBetweenOrderByDate(LocalDate dateFrom, LocalDate dateTo);
    List<TransactionEntity> findAllByAccountIdAndDateBetweenOrderByDate(Long accountId, LocalDate dateFrom, LocalDate dateTo);

}
