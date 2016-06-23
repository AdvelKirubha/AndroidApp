package com.monitor.home;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.homemonitoring.HomeScreen;
import com.homemonitoring.R;
import com.utils.DatabaseHelper;
import com.telnet.PioneerController;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    LinearLayout powerON, powerOFF;
    DatabaseHelper dbase;
    PioneerController pioneer = null;
    HomeScreen homescreen;
    Context mContext;
    String ipAddr, SERVER_IP;
    int SERVERPORT;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView =  inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        dbase = new DatabaseHelper(mContext);
        SERVER_IP = dbase.getAtlonaIp();
        Log.d("Ip Address-->",SERVER_IP);
        SERVERPORT = Integer.parseInt("23");
        homescreen = new HomeScreen();


        return mView;
    }

    @Override
    public void onClick(View v) {

    }

    public void connecttoTelnet(){

        if(pioneer!=null && pioneer.isConnected())
            Log.d("Telnet","Connected");
        else {
            new AsyncTask<HomeScreen, Void, Void>() {
                @Override
                protected Void doInBackground(HomeScreen... act) {
                    Log.d("Home Fragment", "Do in Back");
                    try {
                        pioneer = new PioneerController(SERVER_IP, SERVERPORT, homescreen);
                        Log.d("Home Fragment", SERVER_IP+SERVERPORT);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    Log.d("OnPostExecute","Telnet Connected");
                }
            }.execute();
        }
    }

    public void sendCommand(final String commands)
    {
        new AsyncTask<String, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(String... act) {
                Boolean flag = false;
                try {
                    pioneer.sendCommand(commands);
                    flag=true;
                } catch (Exception e) {

                }
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean flag) {
                if (flag) {
                  Log.d("Command","Sent");
                }
            }
        }.execute();
    }

}
