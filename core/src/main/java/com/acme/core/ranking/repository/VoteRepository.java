package com.acme.core.ranking.repository;

import com.acme.core.ranking.domain.Vote;

import java.util.List;

public interface VoteRepository {
    Vote save(Vote vote);

    List<Vote> getAll();
}
