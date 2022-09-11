package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
public class RankingDomain {

    @Getter
    private List<PositionDomain> positions;
}
