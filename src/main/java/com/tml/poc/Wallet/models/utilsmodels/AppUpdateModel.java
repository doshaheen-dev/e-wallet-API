package com.tml.poc.Wallet.models.utilsmodels;

import java.util.Date;

public class AppUpdateModel {

    private String appName="Skygge";
    private String appCode="SKYEGGE_FMCG_SR";
    private String releaseNote="Demo release for Test";
    private String appVersionName="1.2.4";
    private int versionNumber=10;
    private Date updateDateTime=new Date(System.currentTimeMillis());
    private String downloadURL="http://uat.theskygge.com/skygge_apk/apk/test/skygge_fmcg_uat_v5.1_6.apk";

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getReleaseNote() {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote) {
        this.releaseNote = releaseNote;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public void setDownloadURL(String downloadURL) {
        this.downloadURL = downloadURL;
    }
}
