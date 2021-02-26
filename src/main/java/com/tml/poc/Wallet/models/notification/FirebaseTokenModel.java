package com.tml.poc.Wallet.models.notification;

import javax.persistence.*;

/**
 * firebase token update for Mobile user
 */
@Entity
@Table(name = "FirebaseTokenTable")
public class FirebaseTokenModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long firebaseTokenModelId;

    @Column(name = "FirebaseToken")
    private String fcmToken;

    @Column(name = "mobileDeviceID")
    private String deviceID;

    @Column(name = "userid")
    private String userId;

    public long getFirebaseTokenModelId() {
        return firebaseTokenModelId;
    }

    public void setFirebaseTokenModelId(long firebaseTokenModelId) {
        this.firebaseTokenModelId = firebaseTokenModelId;
    }

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
