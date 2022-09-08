package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.Position;
import com.acme.core.ranking.domain.Ranking;
import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
public class GetRankUseCaseImpl implements GetRankUseCase {

    private final VoteRepository voteRepository;

    private final CharacterGateway characterGateway;

    public Ranking execute() {
        var votes = voteRepository.getAll();

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
                .map(id -> Position.builder()
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
                .map(p -> Position.builder()
                        .character(p.getCharacter())
                        .likes(p.getLikes())
                        .dislikes(p.getDislikes())
                        .number(i.getAndIncrement())
                        .build())
                .toList();

        return Ranking.builder()
                .positions(positions)
                .build();
    }
}
