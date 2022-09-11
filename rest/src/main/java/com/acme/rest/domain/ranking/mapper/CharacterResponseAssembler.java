package com.acme.rest.domain.ranking.mapper;

import com.acme.rest.domain.ranking.dto.CharacterResponse;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelCharacterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterResponseAssembler implements RepresentationModelAssembler<MarvelCharacterResponse, CharacterResponse> {

    private final CharacterMapper characterMapper;

    @Override
    public CharacterResponse toModel(MarvelCharacterResponse marvelCharacterResponse) {
        return characterMapper.map(marvelCharacterResponse);
    }
}
