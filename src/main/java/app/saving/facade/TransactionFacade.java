package app.saving.facade;

import app.saving.entity.TransactionCategoryEntity;
import app.saving.entity.TransactionCategoryPatternEntity;
import app.saving.repository.TransactionCategoryPatternRepository;
import app.saving.repository.TransactionCategoryRepository;
import app.saving.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionFacade {
    private final TransactionRepository transactionRepository;
    private final TransactionCategoryRepository transactionCategoryRepository;
    private final TransactionCategoryPatternRepository transactionCategoryPatternRepository;

    @Transactional
    public void updateCategory(UUID transactionId, String category) {
        TransactionCategoryEntity transactionCategoryEntity = transactionCategoryRepository.findByName(category)
                .orElse(new TransactionCategoryEntity(
                        UUID.randomUUID(),
                        category,
                        null
                ));
        transactionRepository.findById(transactionId)
                .map(transactionEntity -> transactionEntity.setCategory(transactionCategoryEntity))
                .map(transactionRepository::save)
                .ifPresent(transactionEntity -> {
                    TransactionCategoryPatternEntity transactionCategoryPatternEntity = new TransactionCategoryPatternEntity()
                            .setId(UUID.randomUUID())
                            .setIbanPattern(transactionEntity.getClientIban())
                            .setOriginalWordingPattern(transactionEntity.getOriginalWording().replaceAll("\\d", "%").trim())
                            .setCategoryId(transactionEntity.getCategory().getId());
                    transactionCategoryPatternRepository.save(transactionCategoryPatternEntity);

                    transactionRepository.updateTransactionCategoryFromPattern(
                            transactionEntity.getCategory().getId(),
                            transactionCategoryPatternEntity.getIbanPattern(),
                            transactionCategoryPatternEntity.getOriginalWordingPattern(),
                            transactionEntity.getAccountId()
                    );

                });
    }
}
