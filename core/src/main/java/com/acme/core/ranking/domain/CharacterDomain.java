package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterDomain {
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
