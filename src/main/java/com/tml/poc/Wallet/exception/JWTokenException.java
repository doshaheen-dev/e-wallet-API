package com.tml.poc.Wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * JWT Token Exception
 * Custom Exception handling
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JWTokenException extends Exception{

    private static final long serialVersionUID = 1L;

    public JWTokenException(String message){
        super(message);
    }
}