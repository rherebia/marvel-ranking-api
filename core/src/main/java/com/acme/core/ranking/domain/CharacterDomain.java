package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class CharacterDomain {
    @Getter
    private Long id;

    private String name;

    private Long comicsCount;

    private Long seriesCount;

    private Long eventsCount;

    private Long storiesCount;

    public Long getAppearancesCount() {
        return comicsCount + seriesCount + eventsCount + storiesCount;
    }
}
