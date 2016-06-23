package com.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rajasingh on 4/29/2016.
 */
public class Subsites {

    @SerializedName("subsiteID")
    private String subsiteId;

    @SerializedName("subsitename")
    private String subsiteName;

    @SerializedName("parentID")
    private String parentId;

    @SerializedName("deviceID")
    private String deviceId;

    @SerializedName("IP")
    private String inetProtocol;

    @SerializedName("userid")
    private String userId;

    public Subsites(String subsiteId, String subsiteName){
        this.subsiteId = subsiteId;
        this.subsiteName = subsiteName;
    }

    public String getSubsiteId() {
        return subsiteId;
    }

    public void setSubsiteId(String subsiteId) {
        this.subsiteId = subsiteId;
    }

    public String getSubsiteName() {
        return subsiteName;
    }

    public void setSubsiteName(String subsiteName) {
        this.subsiteName = subsiteName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInetProtocol() {
        return inetProtocol;
    }

    public void setInetProtocol(String inetProtocol) {
        this.inetProtocol = inetProtocol;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
