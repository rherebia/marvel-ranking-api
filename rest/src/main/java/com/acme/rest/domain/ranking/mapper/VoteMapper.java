package com.acme.rest.domain.ranking.mapper;

import com.acme.core.ranking.domain.VoteDomain;
import com.acme.rest.domain.ranking.dto.VoteResponse;
import com.acme.rest.domain.ranking.entity.VoteEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteEntity domainToEntity(VoteDomain vote);

    VoteDomain entityToDomain(VoteEntity vote);

    VoteResponse domainToResponse(VoteDomain vote);
}
