package com.acme.rest.domain.ranking.config;

import com.acme.core.ranking.gateway.CharacterGateway;
import com.acme.core.ranking.repository.VoteRepository;
import com.acme.core.ranking.usecase.CreateVoteUseCase;
import com.acme.core.ranking.usecase.CreateVoteUseCaseImpl;
import com.acme.core.ranking.usecase.GetCharactersRankingUseCase;
import com.acme.core.ranking.usecase.GetCharactersRankingUseCaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RankingConfig {

    private final VoteRepository voteRepository;

    private final CharacterGateway characterGateway;

    @Bean
    public CreateVoteUseCase createVoteUseCase() {
        return new CreateVoteUseCaseImpl(voteRepository, characterGateway);
    }

    @Bean
    public GetCharactersRankingUseCase getCharactersRankingUseCase() {
        return new GetCharactersRankingUseCaseImpl(voteRepository, characterGateway);
    }
}
