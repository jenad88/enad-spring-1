package com.johnenad.enadspring1.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String details;

    public BadRequestException(String code, String message, String details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
}