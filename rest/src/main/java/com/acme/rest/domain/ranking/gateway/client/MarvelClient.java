package com.acme.rest.domain.ranking.gateway.client;

import com.acme.rest.domain.ranking.gateway.client.dto.MarvelWrapResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "marvelClient", url = "${feign.client.config.marvelClient.host}",
    configuration = { MarvelCredentialsInterceptor.class })
public interface MarvelClient {

    @GetMapping("${feign.client.config.marvelClient.characters.endpoint}/{characterId}")
    MarvelWrapResponse getCharacter(@PathVariable("characterId") Long characterId);
}
