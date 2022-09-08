package com.acme.rest.domain.ranking.repository;

import com.acme.rest.domain.ranking.entity.VoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteJpaRepository extends JpaRepository<VoteEntity, Long> {
}
