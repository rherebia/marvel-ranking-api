package com.acme.rest.domain.shared.utils;

import com.acme.core.shared.exception.MarvelRankingException;
import com.acme.rest.domain.shared.dto.ErrorResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@UtilityClass
@Slf4j
public class ExceptionHandlerUtils {

    public static ResponseEntity<ErrorResponse> createErrorResponseEntity(MarvelRankingException e, HttpStatus status) {
        log.error(e.getMessage(), e);

        var errorResponse = ErrorResponse.builder()
                .message(e.getMessage())
                .status(status.value())
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
