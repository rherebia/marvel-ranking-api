package com.acme.rest.domain.ranking.dto;

import com.acme.core.ranking.domain.VoteKindEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteRequest {
    @NotNull
    private Long characterId;

    @NotNull
    private VoteKindEnum voteKind;
}
