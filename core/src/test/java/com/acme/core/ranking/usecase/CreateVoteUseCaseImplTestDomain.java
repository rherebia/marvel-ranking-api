package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.CharacterDomain;
import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.core.shared.exception.ProcessingErrorException;
import com.acme.core.shared.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CreateVoteUseCaseImplTestDomain {

    @BeforeAll
    public static void setup() {
        Locale.setDefault(new Locale("en", "us"));
    }

    @Test
    void shouldCreateVote() throws MarvelRankingException {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var characterId = 1L;

        var ironMan = CharacterDomain.builder()
                .id(characterId)
                .name("Iron Man")
                .build();

        when(characterGateway.get(characterId)).thenReturn(ironMan);

        var useCase = new CreateVoteUseCaseImpl(voteRepository, characterGateway);

        useCase.execute(characterId, VoteKindEnum.LIKE);

        Mockito.verify(voteRepository).save(any());
    }

    @Test
    void shouldNotCreateVoteIfCharacterNotExist() throws MarvelRankingException {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var useCase = new CreateVoteUseCaseImpl(voteRepository, characterGateway);

        assertThatThrownBy(() -> {
            useCase.execute(1L, VoteKindEnum.LIKE);
        }).isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void shouldThreatProcessingError() throws MarvelRankingException {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var characterId = 1L;

        var ironMan = CharacterDomain.builder()
                .id(characterId)
                .name("Iron Man")
                .build();

        when(characterGateway.get(characterId)).thenReturn(ironMan);
        when(voteRepository.save(any(VoteDomain.class))).thenThrow(RuntimeException.class);

        var useCase = new CreateVoteUseCaseImpl(voteRepository, characterGateway);

        assertThatThrownBy(() -> {
            useCase.execute(characterId, VoteKindEnum.LIKE);
        }).isInstanceOf(ProcessingErrorException.class);
    }
}