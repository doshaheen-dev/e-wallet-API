package com.tml.poc.Wallet.models.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ChangeMPINModel {

    @NotEmpty(message = "Old MPIN can not be empty")
    @NotNull(message = "Old MPIN can not be Null")
    private String oldMPIN;

    @NotEmpty(message = "New MPIN can not be empty")
    @NotNull(message = "New MPIN can not be Null")
    private String newMpin;

    @NotEmpty(message = "Request ID can not be empty")
    @NotNull(message = "Request ID can not be Null")
    private String requestID;

    @NotEmpty(message = "User ID can not be empty")
    @NotNull(message = "User ID can not be Null")
    private long userID;

    public String getOldMPIN() {
        return oldMPIN;
    }

    public void setOldMPIN(String oldMPIN) {
        this.oldMPIN = oldMPIN;
    }

    public String getNewMpin() {
        return newMpin;
    }

    public void setNewMpin(String newMpin) {
        this.newMpin = newMpin;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}
