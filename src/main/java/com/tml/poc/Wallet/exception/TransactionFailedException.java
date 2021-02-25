package com.tml.poc.Wallet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * THis exception Called if Wallet Transaction Get Failed
 */
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class TransactionFailedException extends Exception{

    private static final long serialVersionUID = 1233L;

    public TransactionFailedException(String message){
        super(message);
    }
}