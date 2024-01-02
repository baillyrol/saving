package app.saving.gateway;

import app.saving.gateway.dto.AccountResponse;
import app.saving.gateway.dto.TransactionResponse;
import app.saving.gateway.dto.response.GetTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class PowensGateway {
    @Value("${powens.client.id}")
    private String clientId;
    @Value("${powens.client.secret}")
    private String clientSecret;

    private final RestClient powensRestClient;

    public AccountResponse getAccounts(String token) {
        String uri = "/users/me/accounts";

        return powensRestClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(AccountResponse.class);
    }

    public TransactionResponse getTransactions(String accountId, String token) {
        String uri = "/users/me/accounts/" + accountId + "/transactions?limit=1000";

        return powensRestClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .retrieve()
                .body(TransactionResponse.class);
    }

    public GetTokenResponse getToken(String code) {

        return powensRestClient
                .post()
                .uri(uriBuilder -> uriBuilder
                        .path("/auth/token/access")
                        .queryParam("code", code)
                        .queryParam("client_id", clientId)
                        .queryParam("client_secret", clientSecret)
                        .queryParam("grant_type", "authorization_code")
                        .build()

                )
                .retrieve()
                .body(GetTokenResponse.class);
    }
}
