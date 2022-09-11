package com.acme.core.ranking.usecase;

import com.acme.core.ranking.domain.RankingDomain;
import com.acme.core.ranking.exception.NoVoteFoundException;

public interface GetCharactersRankingUseCase {
    RankingDomain execute() throws NoVoteFoundException;
}
