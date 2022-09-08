package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.shared.exception.ProcessingErrorException;
import com.acme.core.shared.exception.ResourceNotFoundException;

public interface CreateVoteUseCase {
    VoteDomain execute(Long characterId, VoteKindEnum voteKind) throws ProcessingErrorException, ResourceNotFoundException;
}
