package app.saving.gateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GetTokenResponse(@JsonProperty("access_token") String token) {
}
