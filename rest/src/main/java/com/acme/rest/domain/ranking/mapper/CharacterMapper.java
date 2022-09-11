package com.acme.rest.domain.ranking.mapper;

import com.acme.core.ranking.domain.CharacterDomain;
import com.acme.rest.domain.ranking.dto.CharacterResponse;
import com.acme.rest.domain.ranking.dto.CharactersResponse;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelCharacterResponse;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelWrapResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {

    @Mapping(expression = "java(response.getData().getResults().get(0).getName())", target = "name")
    @Mapping(expression = "java(response.getData().getResults().get(0).getId())", target = "id")
    @Mapping(expression = "java(response.getData().getResults().get(0).getComics().getAvailable())", target = "comicsCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getEvents().getAvailable())", target = "eventsCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getSeries().getAvailable())", target = "seriesCount")
    @Mapping(expression = "java(response.getData().getResults().get(0).getStories().getAvailable())", target = "storiesCount")
    CharacterDomain clientToDomain(MarvelWrapResponse response);

    @Mapping(source = "response.data.limit", target = "pageSize")
    @Mapping(expression = "java(response.getData().getOffset() + 1)", target = "page")
    @Mapping(source = "response.data.results", target = "data")
    CharactersResponse clientToResponse(MarvelWrapResponse response);

    CharacterResponse map(MarvelCharacterResponse response);

    List<CharacterResponse> map(List<MarvelCharacterResponse> characters);
}
