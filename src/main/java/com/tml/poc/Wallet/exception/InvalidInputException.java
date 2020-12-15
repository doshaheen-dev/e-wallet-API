package com.tml.poc.Wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidInputException extends Exception{

    private static final long serialVersionUID = 2L;

    public InvalidInputException(String message){
        super(message);
    }
}