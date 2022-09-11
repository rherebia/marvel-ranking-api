package com.acme.rest.domain.ranking.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class RankingPositionResponse extends RepresentationModel<RankingPositionResponse> {
    private CharacterResponse character;

    private Integer number;

    private Long likes;

    private Long dislikes;

    private Long score;

    private Long appearances;
}
