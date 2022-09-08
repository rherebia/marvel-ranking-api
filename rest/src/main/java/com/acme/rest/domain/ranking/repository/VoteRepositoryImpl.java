package com.acme.rest.domain.ranking.repository;

import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.repository.VoteRepository;
import com.acme.rest.domain.ranking.mapper.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepository {

    private final VoteJpaRepository voteJpaRepository;

    private final VoteMapper voteMapper;

    @Override
    public VoteDomain save(VoteDomain vote) {
        return voteMapper.entityToDomain(voteJpaRepository.save(voteMapper.domainToEntity(vote)));
    }

    @Override
    public List<VoteDomain> getAll() {
        return voteJpaRepository.findAll().stream()
                .map(voteMapper::entityToDomain)
                .toList();
    }
}
