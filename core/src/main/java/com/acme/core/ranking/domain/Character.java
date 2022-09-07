package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private Long id;

    private String name;

    private Long comicsCount;

    private Long seriesCount;

    private Long eventsCount;

    public Long getAppearancesCount() {
        return comicsCount + seriesCount + eventsCount;
    }
}
