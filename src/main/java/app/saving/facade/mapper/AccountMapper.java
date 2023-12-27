package app.saving.facade.mapper;

import app.saving.entity.AccountEntity;
import app.saving.gateway.dto.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountMapper {

    public AccountEntity from(Account account, UUID connectionId){
        return new AccountEntity()
                .setId(UUID.randomUUID())
                .setConnectionId(connectionId)
                .setExternalAccountId(account.id().toString())
                .setExternalConnectionId(account.idConnection())
                .setComingBalance(account.comingBalance())
                .setFormattedBalance(account.formattedBalance())
                .setIban(account.iban());
    }
}
