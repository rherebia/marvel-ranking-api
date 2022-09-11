package com.acme.rest.domain.ranking.mapper;

import com.acme.core.ranking.domain.PositionDomain;
import com.acme.rest.domain.ranking.dto.RankingPositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RankingPositionMapper {

    @Mapping(source = "character.appearancesCount", target = "appearances")
    RankingPositionResponse map(PositionDomain position);
}
