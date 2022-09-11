package com.acme.rest.domain.ranking.controller;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.VoteRequest;
import com.acme.rest.domain.ranking.dto.VoteResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Votes")
@RequestMapping("votes")
public interface VotesController {

    @PostMapping
    public ResponseEntity<VoteResponse> create(@RequestBody VoteRequest vote) throws MarvelRankingException;
}
