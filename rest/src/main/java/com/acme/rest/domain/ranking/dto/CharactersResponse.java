package com.acme.rest.domain.ranking.dto;

import lombok.Data;

import java.util.List;

@Data
public class CharactersResponse {
    private int page;

    private int pageSize;

    private List<CharacterResponse> data;
}
