package com.acme.rest.domain.ranking.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharactersResponse {
    private int page;

    private int pageSize;

    private List<CharacterResponse> data;
}
