package com.acme.rest.domain.shared.config;

import com.acme.core.shared.exception.ProcessingErrorException;
import com.acme.core.shared.exception.ResourceNotFoundException;
import com.acme.rest.domain.shared.dto.ErrorResponse;
import com.acme.rest.domain.shared.utils.ExceptionHandlerUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class SharedExceptionHandler {

    @ExceptionHandler(ProcessingErrorException.class)
    public ResponseEntity<ErrorResponse> handle(ProcessingErrorException e) {
        return ExceptionHandlerUtils.createErrorResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ResourceNotFoundException e) {
        return ExceptionHandlerUtils.createErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

}
