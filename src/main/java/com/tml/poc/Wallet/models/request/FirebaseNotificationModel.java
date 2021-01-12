package com.tml.poc.Wallet.models.request;

public class FirebaseNotificationModel {

private String to;
private Notification notification;
private long priority;

public String getTo() {
return to;
}

public void setTo(String to) {
this.to = to;
}

public Notification getNotification() {
return notification;
}

public void setNotification(Notification notification) {
this.notification = notification;
}

public long getPriority() {
return priority;
}

public void setPriority(long priority) {
this.priority = priority;
}

}