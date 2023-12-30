package app.saving.repository;


import app.saving.entity.TransactionCategoryEntity;
import app.saving.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionCategoryRepository extends JpaRepository<TransactionCategoryEntity, UUID> {

    Optional<TransactionCategoryEntity> findByName(String category);
}
