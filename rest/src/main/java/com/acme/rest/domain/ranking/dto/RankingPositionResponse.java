package com.acme.rest.domain.ranking.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class RankingPositionResponse extends RepresentationModel<RankingPositionResponse> {
    private CharacterResponse character;

    private Integer number;

    private Long likes;

    private Long dislikes;

    private Long score;

    private Long appearances;
}
