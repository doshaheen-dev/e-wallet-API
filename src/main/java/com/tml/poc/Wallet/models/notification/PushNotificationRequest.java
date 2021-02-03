package com.tml.poc.Wallet.models.notification;

import java.util.List;
import java.util.Map;

public class PushNotificationRequest {

    private String title;
    private String message;
    private String imageUrl;
    private List<String> tokenList;
    private Map<String, String> data;
    private String topic;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getTokenList() {
        return tokenList;
    }

    public void setTokenList(List<String> tokenList) {
        this.tokenList = tokenList;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
