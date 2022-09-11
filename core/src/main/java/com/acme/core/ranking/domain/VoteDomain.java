package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class VoteDomain {

    @Getter
    Long id;

    @Getter
    Long characterId;

    @Getter
    VoteKindEnum voteKind;
}
