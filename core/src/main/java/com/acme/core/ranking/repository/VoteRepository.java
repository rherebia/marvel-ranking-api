package com.acme.core.ranking.repository;

import com.acme.core.ranking.domain.Vote;

public interface VoteRepository {
    Vote save(Vote vote);
}
