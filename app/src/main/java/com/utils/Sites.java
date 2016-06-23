package com.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rajasingh on 29/4/16.
 */
public class Sites {

    @SerializedName("siteID")
    private String siteId;

    @SerializedName("userID")
    private String userId;

    @SerializedName("customerid")
    private String customerId;

    @SerializedName("sitename")
    private String siteName;

    @SerializedName("deviceID")
    private String deviceId;

    @SerializedName("IP")
    private String mIp;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("issubsites")
    private String isSubsites;

    public Sites(String siteName, String siteId){
        this.siteName = siteName;
        this.siteId = siteId;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getmIp() {
        return mIp;
    }

    public void setmIp(String mIp) {
        this.mIp = mIp;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getIsSubsites() {
        return isSubsites;
    }

    public void setIsSubsites(String isSubsites) {
        this.isSubsites = isSubsites;
    }

}
