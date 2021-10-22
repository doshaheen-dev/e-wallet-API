package com.tml.poc.Wallet.jwt.resorce;

/**
 * JWT Exception Authentication
 * If JWT is not authenticate
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

