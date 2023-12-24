package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaginationLinks(
        @JsonProperty("prev")
        Link prev,
        @JsonProperty("self")
        Link self,
        @JsonProperty("next")
        Link next
) {
}
