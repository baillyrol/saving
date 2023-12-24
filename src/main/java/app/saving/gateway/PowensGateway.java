package app.saving.gateway;

import app.saving.gateway.dto.AccountResponse;
import app.saving.gateway.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PowensGateway {

    @Value("${powens.token}")
    private String token;

    @Value("${powens.url.api}")
    private String baseUrl;

    @Value("${powens.url.accounts}")
    private String getAccountsUrl;

    @Value("${powens.url.transactions}")
    private String getTransactions;

    private final RestClient restClient = RestClient.create();

    public AccountResponse getAccounts(Long connectionId) {
        String uri = baseUrl + getAccountsUrl + connectionId.toString() + "/accounts";

        return restClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + token)
                .retrieve()
                .body(AccountResponse.class);
    }

    public TransactionResponse getTransactions(Long accountId) {
        String uri = baseUrl + getTransactions + accountId.toString() + "/transactions?limit=1000";

        return restClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + token)
                .retrieve()
                .body(TransactionResponse.class);
    }

    public TransactionResponse getNextTransactionPage(String uri) {
        return restClient
                .get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + token)
                .retrieve()
                .body(TransactionResponse.class);
    }

}