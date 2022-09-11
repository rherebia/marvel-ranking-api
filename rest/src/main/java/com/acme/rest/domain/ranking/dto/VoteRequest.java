package com.acme.rest.domain.ranking.dto;

import com.acme.core.ranking.domain.VoteKindEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class VoteRequest {
    @NotNull
    private Long characterId;

    @NotNull
    private VoteKindEnum voteKind;
}
