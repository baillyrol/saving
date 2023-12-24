package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TransactionResponse(
        @JsonProperty("first_date")
        String firstDate,
        @JsonProperty("last_date")
        String lastDate,
        @JsonProperty("transactions")
        List<Transaction> transactions,
        @JsonProperty("total")
        Integer total,
        @JsonProperty("_links")
        PaginationLinks paginationLinks
) {
}
