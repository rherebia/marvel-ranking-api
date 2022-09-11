package com.acme.rest.domain.ranking.controller;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.CharacterResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Characters")
@RequestMapping("characters")
public interface CharactersController {

    @Operation(summary = "Get characters with pagination")
    @GetMapping
    public ResponseEntity<PagedModel<CharacterResponse>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                                                                @Parameter(description = "Filter by name starting with this value") @RequestParam(required = false) String name) throws MarvelRankingException;

}
