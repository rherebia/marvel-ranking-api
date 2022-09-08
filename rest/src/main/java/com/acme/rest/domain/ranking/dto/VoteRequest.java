package com.acme.rest.domain.ranking.dto;

import com.acme.core.ranking.domain.VoteKindEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VoteRequest {
    @NotNull
    private Long characterId;

    @NotNull
    private VoteKindEnum voteKind;
}
