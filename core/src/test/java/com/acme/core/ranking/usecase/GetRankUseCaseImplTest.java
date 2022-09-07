package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.Character;
import com.acme.core.ranking.domain.Position;
import com.acme.core.ranking.domain.Vote;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetRankUseCaseImplTest {

    @Test
    void shouldCreateRankingWithVotes() {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var votes = List.of(
                Vote.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                Vote.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                Vote.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                Vote.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.DISLIKE)
                        .build(),
                Vote.builder()
                        .characterId(2L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build()
        );

        when(voteRepository.getAll()).thenReturn(votes);
        when(characterGateway.get(1L)).thenReturn(Character.builder()
                        .id(1L)
                .build());
        when(characterGateway.get(2L)).thenReturn(Character.builder()
                .id(2L)
                .build());

        var useCase = new GetRankUseCaseImpl(voteRepository, characterGateway);

        var ranking = useCase.execute();

        assertThat(ranking.getPositions())
                .extracting(Position::getNumber, p -> p.getCharacter().getId(), Position::getScore)
                .containsExactly(
                        tuple(1, 1L, 2L),
                        tuple(2, 2L, 1L)
                );
    }

    @Test
    void shouldCreateRankingWithTiedCharacters() {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var votes = List.of(
                Vote.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                Vote.builder()
                        .characterId(2L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build()
        );

        when(voteRepository.getAll()).thenReturn(votes);
        when(characterGateway.get(1L)).thenReturn(Character.builder()
                .id(1L)
                .comicsCount(0L)
                .seriesCount(0L)
                .eventsCount(0L)
                .build());
        when(characterGateway.get(2L)).thenReturn(Character.builder()
                .id(2L)
                .comicsCount(1L)
                .seriesCount(0L)
                .eventsCount(0L)
                .build());

        var useCase = new GetRankUseCaseImpl(voteRepository, characterGateway);

        var ranking = useCase.execute();

        assertThat(ranking.getPositions())
                .extracting(Position::getNumber, p -> p.getCharacter().getId(), Position::getScore)
                .containsExactly(
                        tuple(1, 2L, 1L),
                        tuple(2, 1L, 1L)
                );
    }
}