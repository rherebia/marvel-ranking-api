package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionDomain {
    private CharacterDomain character;

    private Integer number;

    private Long likes;

    private Long dislikes;

    public Long getScore() {
        return likes - dislikes;
    }
}
