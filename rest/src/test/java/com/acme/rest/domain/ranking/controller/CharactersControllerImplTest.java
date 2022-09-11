package com.acme.rest.domain.ranking.controller;

import com.acme.rest.utils.BaseControllerTest;
import com.acme.rest.utils.JsonFileReader;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CharactersControllerImplTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${feign.client.config.marvelClient.characters.endpoint}")
    private String marvelCharactersEndpoint;

    @Value("${feign.client.config.marvelClient.publicKey}")
    private String publicKey;

    @Test
    void shouldGetAllCharacters() throws Exception {
        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(JsonFileReader.getJson("json/marvel/characters-get-response-200.json"))));

        mockMvc.perform(get("/characters"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonFileReader.getJson("json/app/characters-get-default-response-200.json"), true));

        verify(getRequestedFor(urlPathEqualTo(marvelCharactersEndpoint))
                .withQueryParam("offset", equalTo("0"))
                .withQueryParam("limit", equalTo("20"))
                .withQueryParam("apikey", equalTo(publicKey))
                .withQueryParam("ts", matching(".*"))
                .withQueryParam("hash", matching(".*"))
        );
    }

    @Test
    void shouldGetAllCharactersWithPagination() throws Exception {
        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(JsonFileReader.getJson("json/marvel/characters-get-response-200.json"))));

        mockMvc.perform(get("/characters")
                        .queryParam("page", "1")
                        .queryParam("pageSize",  "10"))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonFileReader.getJson("json/app/characters-get-customized-pagination-response-200.json"), true));

        verify(getRequestedFor(urlPathEqualTo(marvelCharactersEndpoint))
                .withQueryParam("offset", equalTo("1"))
                .withQueryParam("limit", equalTo("10"))
                .withQueryParam("apikey", equalTo(publicKey))
                .withQueryParam("ts", matching(".*"))
                .withQueryParam("hash", matching(".*"))
        );
    }

    @Test
    void shouldGetAllCharactersFilteringByName() throws Exception {
        stubFor(WireMock.get(WireMock.urlPathEqualTo(marvelCharactersEndpoint))
                .willReturn(aResponse()
                        .withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(JsonFileReader.getJson("json/marvel/characters-get-response-200.json"))));

        var characterName = "Iron";

        mockMvc.perform(get("/characters")
                        .queryParam("name", characterName))
                .andExpect(status().isOk())
                .andExpect(content().json(JsonFileReader.getJson("json/app/characters-get-filtering-by-name-response-200.json"), true));

        verify(getRequestedFor(urlPathEqualTo(marvelCharactersEndpoint))
                .withQueryParam("offset", equalTo("0"))
                .withQueryParam("limit", equalTo("20"))
                .withQueryParam("limit", equalTo("20"))
                .withQueryParam("nameStartsWith", equalTo(characterName))
                .withQueryParam("ts", matching(".*"))
                .withQueryParam("hash", matching(".*"))
        );
    }
}