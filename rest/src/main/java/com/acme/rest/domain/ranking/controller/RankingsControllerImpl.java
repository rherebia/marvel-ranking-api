package com.acme.rest.domain.ranking.controller;

import com.acme.core.ranking.domain.PositionDomain;
import com.acme.core.ranking.usecase.GetCharactersRankingUseCase;
import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.RankingPositionResponse;
import com.acme.rest.domain.ranking.mapper.RankingPositionResponseAssembler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RankingsControllerImpl implements RankingsController {

    private final GetCharactersRankingUseCase getCharactersRankingUseCase;

    private final RankingPositionResponseAssembler rankingPositionResponseAssembler;

    private final PagedResourcesAssembler<PositionDomain> pagedResourcesAssembler;

    @Override
    public ResponseEntity<PagedModel<RankingPositionResponse>> getAll(Integer pageNumber, Integer pageSize) throws MarvelRankingException {
        var ranking = getCharactersRankingUseCase.execute();
        Page<PositionDomain> page = new PageImpl<>(ranking.getPositions(), PageRequest.of(pageNumber, pageSize), ranking.getPositions().size());
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(page, rankingPositionResponseAssembler));
    }
}
