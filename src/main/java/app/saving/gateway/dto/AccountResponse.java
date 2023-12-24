package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record AccountResponse(
        @JsonProperty("balance")
        Double balance,
        @JsonProperty("accounts")
        List<Account> accounts
) {
}
