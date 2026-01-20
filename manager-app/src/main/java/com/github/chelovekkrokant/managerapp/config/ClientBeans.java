package com.github.chelovekkrokant.managerapp.config;

import com.github.chelovekkrokant.managerapp.client.RestClientProductRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductRestClient restClientProductRestClient(
            @Value("${online-market.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${online-market.services.catalogue.username:}") String catalogueUsername,
            @Value("${online-market.services.catalogue.password:}") String cataloguePassword) {
        return new RestClientProductRestClient(RestClient
                .builder()
                .baseUrl(catalogueBaseUri )
                .requestInterceptor(new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))

                .build());
    }
}
