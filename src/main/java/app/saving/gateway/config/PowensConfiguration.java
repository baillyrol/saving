package app.saving.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class PowensConfiguration {

    @Bean
    public RestClient powensRestClient( @Value("${powens.url.api}") String baseUrl){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }
}
