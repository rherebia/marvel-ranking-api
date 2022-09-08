package com.acme.rest.domain.ranking.controller;

import com.acme.core.ranking.usecase.CreateVoteUseCase;
import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.ranking.dto.VoteRequest;
import com.acme.rest.domain.ranking.dto.VoteResponse;
import com.acme.rest.domain.ranking.mapper.VoteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

    private final CreateVoteUseCase createVoteUseCase;

    private final VoteMapper voteMapper;

    @Override
    public ResponseEntity<VoteResponse> create(VoteRequest vote) throws MarvelRankingException {
        var voteResponse = voteMapper.domainToResponse(createVoteUseCase.execute(vote.getCharacterId(), vote.getVoteKind()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(voteResponse.getId())
                .toUri();


        return ResponseEntity.created(location)
                .body(voteResponse);
    }

}
