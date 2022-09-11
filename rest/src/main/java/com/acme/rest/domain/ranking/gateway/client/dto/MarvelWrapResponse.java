package com.acme.rest.domain.ranking.gateway.client.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MarvelWrapResponse {
    private Integer code;
    private String status;

    private MarvelDataResponse data;
}
