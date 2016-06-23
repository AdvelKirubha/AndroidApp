package com.utils;

/**
 * Created by rajasingh on 2/5/16.
 */
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Devices implements Comparable{

    @SerializedName("deviceconfigid")
    private String devConfigId;

    @SerializedName("Userid")
    private String userId;

    @SerializedName("DeviceID")
    private String deviceId;

    @SerializedName("inputoutputtype")
    private String inoutType;

    @SerializedName("device")
    private String devName;

    @SerializedName("brand")
    private String devBrand;

    @SerializedName("model")
    private String devModel;

    @SerializedName("protocol")
    private String devProtocol;

    @SerializedName("controldevice")
    private String controlDevice;

    @SerializedName("iodeviceid")
    private String inpDeviceId;

    @SerializedName("devicename")
    private String deviceName;

    @SerializedName("currentrowid")
    private String currowId;

    @SerializedName("currentinput")
    private String curInput;

    @SerializedName("portnumber")   //ags
    private String portNum;

    @SerializedName("devicenumber")   //ags
    private String devNumber;


    @SerializedName("isactive")
    private String isActive;

    @SerializedName("createdon")
    private String createdOn;

    @SerializedName("modifiedon")
    private String modifiedOn;

    @SerializedName("siteID")
    private String siteId;

    @SerializedName("isdiv")
    private String isDiv;

  /*  public Devices(String devConfigId, String devName){
        this.devConfigId = devConfigId;
        this.devName = devName;
    }*/

    public String getDevConfigId() {
        return devConfigId;
    }

    public void setDevConfigId(String devConfigId) {
        this.devConfigId = devConfigId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getInoutType() {
        return inoutType;
    }

    public void setInoutType(String inoutType) {
        this.inoutType = inoutType;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevBrand() {
        return devBrand;
    }

    public void setDevBrand(String devBrand) {
        this.devBrand = devBrand;
    }

    public String getDevModel() {
        return devModel;
    }

    public void setDevModel(String devModel) {
        this.devModel = devModel;
    }

    public String getDevProtocol() {
        return devProtocol;
    }

    public void setDevProtocol(String devProtocol) {
        this.devProtocol = devProtocol;
    }

    public String getControlDevice() {
        return controlDevice;
    }

    public void setControlDevice(String controlDevice) {
        this.controlDevice = controlDevice;
    }

    public String getInpDeviceId() {
        return inpDeviceId;
    }

    public void setInpDeviceId(String inpDeviceId) {
        this.inpDeviceId = inpDeviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getCurrowId() {
        return currowId;
    }

    public void setCurrowId(String currowId) {
        this.currowId = currowId;
    }

    public String getCurInput() {
        return curInput;
    }

    public void setCurInput(String curInput) {
        this.curInput = curInput;
    }

    public String getPortNum() {
        return portNum;
    }

    public void setPortNum(String portNum) {
        this.portNum = portNum;
    }

    public String getDeviceNumber() {
        return devNumber;
    }

    public void setDeviceNumber(String devNumber) {
        this.devNumber = devNumber;
    }



    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getIsDiv() {
        return isDiv;
    }

    public void setIsDiv(String isDiv) {
        this.isDiv = isDiv;
    }

    @Override
    public int compareTo(Object another) {
        Devices devices= (Devices) another;

        return this.portNum.compareTo(devices.getPortNum());
    }
}