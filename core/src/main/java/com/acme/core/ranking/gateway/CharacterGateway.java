package com.acme.core.ranking.gateway;

import com.acme.core.ranking.domain.Character;

public interface CharacterGateway {
    Character get(Long characterId);
}
