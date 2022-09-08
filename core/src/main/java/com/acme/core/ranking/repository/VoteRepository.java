package com.acme.core.ranking.repository;

import com.acme.core.ranking.domain.VoteDomain;

import java.util.List;

public interface VoteRepository {
    VoteDomain save(VoteDomain vote);

    List<VoteDomain> getAll();
}
