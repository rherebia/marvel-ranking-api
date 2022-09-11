package com.acme.rest.domain.ranking.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Setter
@Getter
public class CharacterResponse extends RepresentationModel<CharacterResponse> {
    private Long id;

    private String name;
}
