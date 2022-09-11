package com.acme.rest.domain.ranking.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class CharacterResponse extends RepresentationModel<CharacterResponse> {
    private Long id;

    private String name;
}
