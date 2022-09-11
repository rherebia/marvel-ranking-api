package com.acme.rest.domain.ranking.mapper;

import com.acme.core.ranking.domain.CharacterDomain;
import com.acme.rest.domain.ranking.dto.CharacterResponse;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelCharacterResponse;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelWrapResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(expression = "java(response.getData().getResults().get(0).getName())", target = "name")
    @Mapping(expression = "java(response.getData().getResults().get(0).getId())", target = "id")
    @Mapping(expression = "java(response.getData().getResults().get(0).getComics().getAvailable())", target = "comicsCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getEvents().getAvailable())", target = "eventsCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getSeries().getAvailable())", target = "seriesCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getStories().getAvailable())", target = "storiesCount")
    CharacterDomain clientToDomain(MarvelWrapResponse response);

    CharacterResponse map(MarvelCharacterResponse response);
}
