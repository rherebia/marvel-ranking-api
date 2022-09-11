package com.acme.rest.domain.ranking.controller;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.RankingPositionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Rankings")
@RequestMapping("rankings")
public interface RankingsController {

    @Operation(summary = "Get rankings with voted characters")
    @GetMapping
    public ResponseEntity<PagedModel<RankingPositionResponse>> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                                      @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) throws MarvelRankingException;
}
