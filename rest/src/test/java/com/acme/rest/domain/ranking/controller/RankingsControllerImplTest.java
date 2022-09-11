package com.acme.rest.domain.ranking.controller;

import com.acme.core.ranking.domain.VoteDomain;
import com.acme.core.ranking.domain.VoteKindEnum;
import com.acme.core.ranking.repository.VoteRepository;
import com.acme.rest.utils.BaseControllerTest;
import com.acme.rest.utils.JsonFileReader;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RankingsControllerImplTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${feign.client.config.marvelClient.characters.endpoint}")
    private String marvelCharactersEndpoint;

    @Value("${feign.client.config.marvelClient.publicKey}")
    private String publicKey;

    @MockBean
    private VoteRepository voteRepository;

    @Test
    void shouldGetCharactersRanking() throws Exception {
        when(voteRepository.getAll()).thenReturn(List.of(
                VoteDomain.builder()
                        .characterId(1L)
                        .voteKind(VoteKindEnum.LIKE)
                        .build()
        ));

        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint + "/1"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(JsonFileReader.getJson("json/marvel/characters-get-response-200.json"))));

        mockMvc.perform(get("/rankings"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonFileReader.getJson("json/app/rankings-get-response-200.json"), true));

        verify(getRequestedFor(urlPathEqualTo(marvelCharactersEndpoint + "/1"))
                .withQueryParam("apikey", equalTo(publicKey))
                .withQueryParam("ts", matching(".*"))
                .withQueryParam("hash", matching(".*"))
        );
    }
}