package com.acme.rest.domain.ranking.gateway;

import com.acme.core.ranking.domain.CharacterDomain;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.rest.domain.ranking.gateway.client.MarvelClient;
import com.acme.rest.domain.ranking.mapper.CharacterMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterGatewayImpl implements CharacterGateway {

    private final MarvelClient marvelClient;

    private final CharacterMapper characterMapper;

    @Override
    public CharacterDomain get(Long characterId) {
        try {
            return characterMapper.clientToDomain(marvelClient.getCharacter(characterId));
        } catch (FeignException.NotFound e) {
            return null;
        }
    }
}
