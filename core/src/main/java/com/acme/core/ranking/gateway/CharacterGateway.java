package com.acme.core.ranking.gateway;

import com.acme.core.ranking.domain.CharacterDomain;

public interface CharacterGateway {
    CharacterDomain get(Long characterId);
}
