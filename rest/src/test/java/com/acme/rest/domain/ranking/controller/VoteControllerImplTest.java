package com.acme.rest.domain.ranking.controller;

import com.acme.rest.utils.BaseControllerTest;
import com.acme.rest.utils.JsonFileReader;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VoteControllerImplTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${feign.client.config.marvelClient.characters.endpoint}")
    private String marvelCharactersEndpoint;

    @Test
    void shouldCreateVote() throws Exception {
        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint + "/1"))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(JsonFileReader.getJson("json/marvel/characters-get-response-200.json"))));

        mockMvc.perform(post("/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonFileReader.getJson("json/app/votes-post-request-200.json")))
                .andExpect(status().isCreated())
                .andExpect(header().string(LOCATION, "http://localhost/votes/1"));

    }

    @Test
    void shouldNotCreateVoteWhenCharacterNotExist() throws Exception {
        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint + "/1"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        mockMvc.perform(post("/votes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonFileReader.getJson("json/app/votes-post-request-200.json")))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(JsonFileReader.getJson("json/app/votes-post-response-400.json")));

    }

}