package com.tml.poc.Wallet.jwt.resorce;

import java.io.Serializable;

/**
 * JWT TOKEN Response model
 * For POC
 */
public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}