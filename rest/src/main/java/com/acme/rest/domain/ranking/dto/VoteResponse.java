package com.acme.rest.domain.ranking.dto;

import com.acme.core.ranking.domain.VoteKindEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteResponse {
    private Long id;
    private Long characterId;
    private VoteKindEnum voteKind;
}
