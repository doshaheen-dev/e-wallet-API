package com.tml.poc.Wallet.models.reponse;

public class DataFoundModel {
    private long id;
    private boolean isFound;
    private String code;

    public DataFoundModel(boolean isFound, String code) {
        this.isFound = isFound;
        this.code = code;
    }

    public DataFoundModel(long id, boolean isFound, String code) {
        this.id = id;
        this.isFound = isFound;
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
