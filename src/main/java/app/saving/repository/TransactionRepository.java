package app.saving.repository;


import app.saving.entity.TransactionEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends CrudRepository<TransactionEntity, UUID> {

    List<TransactionEntity> findAllByAccountIdAndDateBetweenOrderByDate(UUID accountId, LocalDate dateFrom, LocalDate dateTo);

    boolean existsByExternalId(String externalId);

    @Modifying
    @Query(value = "UPDATE TRANSACTION SET category_id = :categoryId WHERE (client_iban like :ibanPattern OR original_wording like :originalWordingPattern) and category_id is null", nativeQuery = true)
    void updateTransactionCategoryFromPattern(UUID categoryId, String ibanPattern, String originalWordingPattern);

}
