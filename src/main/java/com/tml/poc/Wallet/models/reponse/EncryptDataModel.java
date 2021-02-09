package com.tml.poc.Wallet.models.reponse;

public class EncryptDataModel {
    private String requestId;
    private String service;
    private String encryptedKey;
    private String encryptedData;
    private String oaepHashingAlgorithm;
    private String clientInfo;
    private String iv;
    private String optionalParam;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(String encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getOaepHashingAlgorithm() {
        return oaepHashingAlgorithm;
    }

    public void setOaepHashingAlgorithm(String oaepHashingAlgorithm) {
        this.oaepHashingAlgorithm = oaepHashingAlgorithm;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getOptionalParam() {
        return optionalParam;
    }

    public void setOptionalParam(String optionalParam) {
        this.optionalParam = optionalParam;
    }}
