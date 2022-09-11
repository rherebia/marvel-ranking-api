package com.acme.rest.domain.ranking.gateway.client;

import com.acme.rest.domain.ranking.gateway.client.dto.MarvelWrapResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "marvelClient", url = "${feign.client.config.marvelClient.host}",
    configuration = { MarvelCredentialsInterceptor.class })
public interface MarvelClient {

    @GetMapping("${feign.client.config.marvelClient.characters.endpoint}/{characterId}")
    MarvelWrapResponse getCharacter(@PathVariable("characterId") Long characterId);

    @GetMapping("${feign.client.config.marvelClient.characters.endpoint}")
    MarvelWrapResponse getAllCharacters(@RequestParam(value = "offset", defaultValue = "0") Integer page,
                                        @RequestParam(value = "limit", defaultValue = "20") Integer pageSize,
                                        @RequestParam("nameStartsWith") String name);
}
