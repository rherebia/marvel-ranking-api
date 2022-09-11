package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class PositionDomain {

    @Getter
    private CharacterDomain character;

    @Getter
    private Integer number;

    @Getter
    private Long likes;

    @Getter
    private Long dislikes;

    public Long getScore() {
        return likes - dislikes;
    }
}
