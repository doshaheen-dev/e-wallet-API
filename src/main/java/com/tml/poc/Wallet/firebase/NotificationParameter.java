package com.tml.poc.Wallet.firebase;

/**
 * Enum class to define notification parameters such as sound and color
 */
public enum NotificationParameter {
    SOUND("default"),
    COLOR("#FFFF00");

    private String value;

    NotificationParameter(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
