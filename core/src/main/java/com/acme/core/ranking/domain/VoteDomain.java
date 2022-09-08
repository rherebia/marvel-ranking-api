package com.acme.core.ranking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoteDomain {
    Long id;
    Long characterId;
    VoteKindEnum voteKind;
}
