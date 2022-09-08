package com.acme.rest.domain.ranking.gateway.client.dto;

import lombok.Data;

@Data
public class MarvelWrapResponse {
    private Integer code;
    private String status;

    private MarvelDataResponse data;
}
