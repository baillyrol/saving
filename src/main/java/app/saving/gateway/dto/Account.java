package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Account(
        @JsonProperty("id")
        Long id,
        @JsonProperty("id_connection")
        String idConnection,
        @JsonProperty("id_user")
        String idUser,
        @JsonProperty("iban")
        String iban,
        @JsonProperty("original_name")
        String name,
        @JsonProperty("coming_balance")
        Double comingBalance,
        @JsonProperty("formatted_balance")
        String formattedBalance
        ) {
}
