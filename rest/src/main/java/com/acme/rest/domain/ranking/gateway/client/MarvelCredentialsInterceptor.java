package com.acme.rest.domain.ranking.gateway.client;

import feign.RequestInterceptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

public class MarvelCredentialsInterceptor {

    @Value("${feign.client.config.marvelClient.publicKey}")
    private String publicKey;

    @Value("${feign.client.config.marvelClient.privateKey}")
    private String privateKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            var timestamp = Instant.now().toEpochMilli();

            requestTemplate.query("apikey", publicKey);
            requestTemplate.query("ts", "" + timestamp);
            requestTemplate.query("hash", DigestUtils.md5Hex("" + timestamp + privateKey + publicKey));
        };
    }
}
