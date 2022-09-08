package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import com.acme.core.shared.exception.ProcessingErrorException;
import com.acme.core.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CreateVoteUseCaseImpl implements CreateVoteUseCase {

    private final VoteRepository voteRepository;

    private final CharacterGateway characterGateway;

    @Override
    public VoteDomain execute(Long characterId, VoteKindEnum voteKind) throws ProcessingErrorException, ResourceNotFoundException {
        var character = characterGateway.get(characterId);

        if (character == null) {
            throw new ResourceNotFoundException("Character", characterId);
        }

        var vote = VoteDomain.builder()
                .characterId(characterId)
                .voteKind(voteKind)
                .build();

        try {
            return voteRepository.save(vote);
        } catch (Exception e) {
            log.error("", e);
            throw new ProcessingErrorException();
        }
    }
}
