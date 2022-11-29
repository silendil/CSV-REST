package com.luxoft.csvrest.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
@Getter
public class ProblemProcessingException extends RuntimeException {

    private final String message;
}
