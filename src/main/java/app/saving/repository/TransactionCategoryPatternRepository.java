package app.saving.repository;


import app.saving.entity.TransactionCategoryEntity;
import app.saving.entity.TransactionCategoryPatternEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionCategoryPatternRepository extends JpaRepository<TransactionCategoryPatternEntity, UUID> {
}
