package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Transaction(
        @JsonProperty("id")
        String id,
        @JsonProperty("id_account")
        String idAccount,
        @JsonProperty("date")
        String date,
        @JsonProperty("original_wording")
        String originalWording,
        @JsonProperty("value")
        Double value,
        @JsonProperty("formatted_value")
        String formatted_value
) {
}
