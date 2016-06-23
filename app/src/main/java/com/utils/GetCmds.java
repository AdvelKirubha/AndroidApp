package com.utils;

/**
 * Created by rajasingh on 21/4/16.
 */
public class GetCmds {
    String deviceid;
    String isinput;

    public GetCmds(String deviceid, String isinput){
        this.deviceid = deviceid;
        this.isinput = isinput;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getIsinput() {
        return isinput;
    }

    public void setIsinput(String isinput) {
        this.isinput = isinput;
    }
}
