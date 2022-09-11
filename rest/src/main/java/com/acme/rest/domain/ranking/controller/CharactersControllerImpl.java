package com.acme.rest.domain.ranking.controller;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.CharacterResponse;
import com.acme.rest.domain.ranking.gateway.client.MarvelClient;
import com.acme.rest.domain.ranking.gateway.client.dto.MarvelCharacterResponse;
import com.acme.rest.domain.ranking.mapper.CharacterResponseAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CharactersControllerImpl implements CharactersController {

    private final MarvelClient marvelClient;

    private final CharacterResponseAssembler characterResponseAssembler;

    private final PagedResourcesAssembler<MarvelCharacterResponse> pagedResourcesAssembler;

    public ResponseEntity<PagedModel<CharacterResponse>> getAll(Integer pageNumber, Integer pageSize, String name) throws MarvelRankingException {
        var response = marvelClient.getAllCharacters(pageNumber, pageSize, name);
        var characters = response.getData().getResults();
        Page<MarvelCharacterResponse> page = new PageImpl<>(characters, PageRequest.of(pageNumber, pageSize), response.getData().getTotal());
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, characterResponseAssembler));
    }
}
