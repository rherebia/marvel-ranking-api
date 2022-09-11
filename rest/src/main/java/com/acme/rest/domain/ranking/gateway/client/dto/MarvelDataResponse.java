package com.acme.rest.domain.ranking.gateway.client.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MarvelDataResponse {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<MarvelCharacterResponse> results;
}
