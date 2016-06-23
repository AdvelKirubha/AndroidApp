package com.utils;

/**
 * Created by rajasingh on 16/4/16.
 */
        import java.io.IOException;
        import java.net.HttpURLConnection;
        import java.net.URL;

        import android.content.Context;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;

public class CheckInternet {

    private Context context;
    ConnectivityManager connectivitymanager;

    public CheckInternet(Context context){
        this.context = context;
    }

    public boolean hasActiveInternetConnection(){
        connectivitymanager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nwInfo = connectivitymanager.getActiveNetworkInfo();
        boolean connectionExist = false;
        if(nwInfo != null && nwInfo.isConnectedOrConnecting() &&
                connectivitymanager.getActiveNetworkInfo().isAvailable()){
            try{
                HttpURLConnection urlCon = (HttpURLConnection) new URL("http://dmfco.org/admin").openConnection();
                urlCon.setRequestProperty("User-Agent", "Test");
                urlCon.setRequestProperty("Connection", "close");
                urlCon.setConnectTimeout(10000);
                urlCon.connect();
                if(urlCon.getResponseCode() == 200){
                    connectionExist = true;
                }
            }catch(IOException ie){
                ie.printStackTrace();
                connectionExist = false;
            }
        }else{
            connectionExist = false;
        }
        return connectionExist;
    }
}
