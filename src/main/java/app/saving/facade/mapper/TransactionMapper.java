package app.saving.facade.mapper;

import app.saving.entity.TransactionEntity;
import app.saving.gateway.dto.Transaction;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransactionMapper {

    public TransactionEntity from(Transaction transaction, UUID accountId) {
        return new TransactionEntity()
                .setId(UUID.randomUUID())
                .setAccountId(accountId)
                .setExternalId(transaction.id())
                .setDate(LocalDate.parse(transaction.date()))
                .setOriginalWording(transaction.originalWording())
                .setVal(transaction.value())
                .setFormattedValue(transaction.formatted_value());
    }
}
