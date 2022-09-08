package com.acme.rest.domain.ranking.gateway.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class MarvelDataResponse {

    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<MarvelCharacterResponse> results;
}
