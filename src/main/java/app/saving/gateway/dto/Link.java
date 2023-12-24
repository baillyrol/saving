package app.saving.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Link(
        @JsonProperty("href")
        String href
) {
}
