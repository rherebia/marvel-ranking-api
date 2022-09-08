package com.acme.rest.domain.ranking.config;

import com.acme.core.ranking.exception.CharacterNotFoundException;
import com.acme.rest.domain.shared.dto.ErrorResponse;
import com.acme.rest.domain.shared.utils.ExceptionHandlerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class VoteExceptionHandler {

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(CharacterNotFoundException e) {
        return ExceptionHandlerUtils.createErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }
}
