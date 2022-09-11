package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.PositionDomain;
import com.acme.core.ranking.domain.RankingDomain;
import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.exception.NoVoteFoundException;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
public class GetCharactersRankingUseCaseImpl implements GetCharactersRankingUseCase {

    private final VoteRepository voteRepository;

    private final CharacterGateway characterGateway;

    @Override
    public RankingDomain execute() throws NoVoteFoundException {
        var votes = voteRepository.getAll();

        if (CollectionUtils.isEmpty(votes)) {
            throw new NoVoteFoundException();
        }

        var charactersIds = votes.stream()
                .map(VoteDomain::getCharacterId)
                .distinct()
                .toList();

        var charactersLikesCouting = votes.stream()
                .filter(v -> VoteKindEnum.LIKE == v.getVoteKind())
                .collect(groupingBy(VoteDomain::getCharacterId, counting()));

        var charactersDislikesCouting = votes.stream()
                .filter(v -> VoteKindEnum.DISLIKE == v.getVoteKind())
                .collect(groupingBy(VoteDomain::getCharacterId, counting()));

        final AtomicInteger i = new AtomicInteger(1);

        var positions = charactersIds.stream()
                .map(id -> PositionDomain.builder()
                        .character(characterGateway.get(id))
                        .likes(Optional.ofNullable(charactersLikesCouting.get(id)).orElse(0L))
                        .dislikes(Optional.ofNullable(charactersDislikesCouting.get(id)).orElse(0L))
                        .build())
                .sorted((p1, p2) -> {
                    if (p2.getScore().equals(p1.getScore())) {
                        return p2.getCharacter().getAppearancesCount().compareTo(p1.getCharacter().getAppearancesCount());
                    }

                    return p2.getScore().compareTo(p1.getScore());
                })
                .map(p -> PositionDomain.builder()
                        .character(p.getCharacter())
                        .likes(p.getLikes())
                        .dislikes(p.getDislikes())
                        .number(i.getAndIncrement())
                        .build())
                .toList();

        return RankingDomain.builder()
                .positions(positions)
                .build();
    }
}
