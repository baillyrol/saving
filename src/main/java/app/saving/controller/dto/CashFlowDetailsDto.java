package app.saving.controller.dto;

import app.saving.entity.AccountEntity;
import app.saving.entity.CashFlowEntity;
import app.saving.entity.TransactionEntity;

import java.util.List;

public record CashFlowDetailsDto(
        AccountEntity accountEntity,
        CashFlowEntity cashFlow,
        List<TransactionEntity> income,
        List<TransactionEntity> expense
) {
}
