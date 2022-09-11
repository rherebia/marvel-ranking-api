package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.CharacterDomain;
import com.acme.core.ranking.domain.PositionDomain;
import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.exception.NoVoteFoundException;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetCharactersRankingUseCaseImplTest {

    @BeforeAll
    static void setup() {
        Locale.setDefault(new Locale("en", "us"));
    }

    @Test
    void shouldCreateRankingWithVotes() throws NoVoteFoundException {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var votes = List.of(
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.DISLIKE)
                        .build(),
                VoteDomain.builder()
                        .characterId(2L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build()
        );

        when(voteRepository.getAll()).thenReturn(votes);
        when(characterGateway.get(1L)).thenReturn(CharacterDomain.builder()
                        .id(1L)
                .build());
        when(characterGateway.get(2L)).thenReturn(CharacterDomain.builder()
                .id(2L)
                .build());

        var useCase = new GetCharactersRankingUseCaseImpl(voteRepository, characterGateway);

        var ranking = useCase.execute();

        assertThat(ranking.getPositions())
                .extracting(PositionDomain::getNumber, p -> p.getCharacter().getId(), PositionDomain::getScore)
                .containsExactly(
                        tuple(1, 1L, 2L),
                        tuple(2, 2L, 1L)
                );
    }

    @Test
    void shouldCreateRankingWithTiedCharacters() throws NoVoteFoundException {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var votes = List.of(
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build(),
                VoteDomain.builder()
                        .characterId(2L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build()
        );

        when(voteRepository.getAll()).thenReturn(votes);
        when(characterGateway.get(1L)).thenReturn(CharacterDomain.builder()
                .id(1L)
                .comicsCount(0L)
                .seriesCount(0L)
                .eventsCount(0L)
                .storiesCount(0L)
                .build());
        when(characterGateway.get(2L)).thenReturn(CharacterDomain.builder()
                .id(2L)
                .comicsCount(1L)
                .seriesCount(0L)
                .eventsCount(0L)
                .storiesCount(0L)
                .build());

        var useCase = new GetCharactersRankingUseCaseImpl(voteRepository, characterGateway);

        var ranking = useCase.execute();

        assertThat(ranking.getPositions())
                .extracting(PositionDomain::getNumber, p -> p.getCharacter().getId(), PositionDomain::getScore)
                .containsExactly(
                        tuple(1, 2L, 1L),
                        tuple(2, 1L, 1L)
                );
    }

    @Test
    void shouldNotGenerateRankingWithoutVotes() {
        var voteRepository = mock(VoteRepository.class);
        var characterGateway = mock(CharacterGateway.class);

        var useCase = new GetCharactersRankingUseCaseImpl(voteRepository, characterGateway);

        assertThatThrownBy(useCase::execute)
                .isInstanceOf(NoVoteFoundException.class);
    }
}