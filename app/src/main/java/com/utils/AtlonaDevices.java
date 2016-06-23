package com.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rajasingh on 28/5/16.
 */
public class AtlonaDevices {

    @SerializedName("id")
    String atlona_id;

    @SerializedName("name")
    String atlona_name;

    @SerializedName("text")
    String atlona_text;

    @SerializedName("parent_id")
    String atlona_parentId;

    @SerializedName("isdiv")
    String atlona_isDiv;

    @SerializedName("ipaddress")
    String atlona_ipaddress;

    @SerializedName("siteID")
    String atlona_siteId;

    @SerializedName("subsiteID")
    String atlona_subsiteId;

    @SerializedName("DeviceID")
    String atlona_deviceId;

    @SerializedName("issubsites")
    String atlona_issubsites;


    public String getAtlona_id() {
        return atlona_id;
    }

    public void setAtlona_id(String atlona_id) {
        this.atlona_id = atlona_id;
    }

    public String getAtlona_name() {
        return atlona_name;
    }

    public void setAtlona_name(String atlona_name) {
        this.atlona_name = atlona_name;
    }

    public String getAtlona_text() {
        return atlona_text;
    }

    public void setAtlona_text(String atlona_text) {
        this.atlona_text = atlona_text;
    }

    public String getAtlona_parentId() {
        return atlona_parentId;
    }

    public void setAtlona_parentId(String atlona_parentId) {
        this.atlona_parentId = atlona_parentId;
    }

    public String getAtlona_isDiv() {
        return atlona_isDiv;
    }

    public void setAtlona_isDiv(String atlona_isDiv) {
        this.atlona_isDiv = atlona_isDiv;
    }

    public String getAtlona_ipaddress() {
        return atlona_ipaddress;
    }

    public void setAtlona_ipaddress(String atlona_ipaddress) {
        this.atlona_ipaddress = atlona_ipaddress;
    }

    public String getAtlona_siteId() {
        return atlona_siteId;
    }

    public void setAtlona_siteId(String atlona_siteId) {
        this.atlona_siteId = atlona_siteId;
    }

    public String getAtlona_subsiteId() {
        return atlona_subsiteId;
    }

    public void setAtlona_subsiteId(String atlona_subsiteId) {
        this.atlona_subsiteId = atlona_subsiteId;
    }

    public String getAtlona_deviceId() {
        return atlona_deviceId;
    }

    public void setAtlona_deviceId(String atlona_deviceId) {
        this.atlona_deviceId = atlona_deviceId;
    }

    public String getAtlona_issubsites() {
        return atlona_issubsites;
    }

    public void setAtlona_issubsites(String atlona_issubsites) {
        this.atlona_issubsites = atlona_issubsites;
    }
}
