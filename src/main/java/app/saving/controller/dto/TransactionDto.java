package app.saving.controller.dto;

import app.saving.entity.TransactionEntity;

import java.time.LocalDate;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        UUID idAccount,
        LocalDate date,
        String originalWording,
        Double value,
        String formattedValue
) {
    public static TransactionDto create(TransactionEntity transactionEntity) {
        return new TransactionDto(transactionEntity.getId(),
                transactionEntity.getAccountId(),
                transactionEntity.getDate(),
                transactionEntity.getOriginalWording(),
                transactionEntity.getVal(),
                transactionEntity.getFormattedValue());
    }
}



