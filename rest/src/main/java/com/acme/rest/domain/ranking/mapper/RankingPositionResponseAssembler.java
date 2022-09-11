package com.acme.rest.domain.ranking.mapper;

import com.acme.core.ranking.domain.PositionDomain;
import com.acme.rest.domain.ranking.dto.RankingPositionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RankingPositionResponseAssembler implements RepresentationModelAssembler<PositionDomain, RankingPositionResponse> {

    private final RankingPositionMapper rankingPositionMapper;

    @Override
    public RankingPositionResponse toModel(PositionDomain positionDomain) {
        return rankingPositionMapper.map(positionDomain);
    }
}
