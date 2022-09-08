package com.acme.rest.domain.ranking.gateway.client.dto;

import lombok.Data;

@Data
public class MarvelCharacterResponse {
    private Long id;

    private String name;

    private MarvelAppearanceResponse comics;
    private MarvelAppearanceResponse series;
    private MarvelAppearanceResponse stories;
    private MarvelAppearanceResponse events;

}
