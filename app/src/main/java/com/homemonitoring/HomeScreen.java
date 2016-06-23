
package com.homemonitoring;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.utils.CheckInternet;
import com.utils.DatabaseHelper;
import com.utils.Devices;
import com.utils.GetCmds;
import com.utils.GetPrefsValue;
import com.utils.Getuid;
import com.utils.InputDeviceCommands;
import com.utils.JsonParser;
import com.monitor.OtherBluray.OtherBluray;
import com.monitor.appletv.Appletv;
import com.monitor.bluray.Bluray;
import com.monitor.computer.Computer;
import com.monitor.doccam.Doccam;
import com.monitor.dvdbluray.Dvdbluray;
import com.monitor.home.HomeFragment;
import com.monitor.laptop.Laptop;
import com.telnet.PioneerController;
import com.utils.OutputDeviceCommands;
import com.utils.VerticalSeekBar;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomeScreen extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    LinearLayout mHome, mDesktop, mLaptop, mDoccam, mDvd, mConf, mAppletv, mContainer, mSettings, mHelp, mBluray, mOther;
    String appletvConfigId, appletvCurInp, appleDevId, dvdConfigId, dvdCurInp, dvdDevId, lapConfigId, lapCurInp, lapDevId,
            deskConfigId, deskCurInp, deskDevId, docConfigId, docCurInp, docDevid;
    ImageView mleftArrow, mrightArrow, devOnOff, mVolumeUp, mVolumeMute;
    CheckInternet checkInternet;
    HorizontalScrollView hScroll;
    String sToken, sCustId, surl, sApikey, suburl, deviceurl, cmdurl, user_id;
    public static boolean powFlag;
    DatabaseHelper db;
    Getuid getuId;
    ProgressDialog mProgress;
    GetPrefsValue getPrefs;
    private static TextView inputStatus;
    private static TextView outputStatus;
    Context mContext;
    ArrayList<GetCmds> getCmds;
    ArrayList<String> get_devices;
    ArrayList<Devices> get_deviceCommand;
    ArrayList<Devices> get_deviceName;
    ArrayList<Devices> get_inputOutputType;
    String token, atlona_ip, siteId, SERVER_IP,get_devices_list;
    int SERVERPORT;
    PioneerController pioneer = null;
    VerticalSeekBar vSeekbar;
    String ipAddress;
    String j="j";
//    private TelnetClient client = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreenn);

        Intent intent = getIntent();
        siteId = intent.getStringExtra("Site_ID");
        mContext = HomeScreen.this;
        mContainer = (LinearLayout) findViewById(R.id.containerLinear);
        hScroll = (HorizontalScrollView) findViewById(R.id.hScroll);
        mleftArrow = (ImageView) findViewById(R.id.leftArrow);
        mrightArrow = (ImageView) findViewById(R.id.rightArrow);
        mSettings = (LinearLayout) findViewById(R.id.setLinear);
        mHelp = (LinearLayout) findViewById(R.id.helpLinear);
        inputStatus = (TextView) findViewById(R.id.inputStreamTextView);
        outputStatus = (TextView) findViewById(R.id.statusStreamTextView);



        //inputStatus.setMovementMethod(new ScrollingMovementMethod());
        //outputStatus.setMovementMethod(new ScrollingMovementMethod());

        vSeekbar = (VerticalSeekBar) findViewById(R.id.seekBar1);
        mVolumeUp = (ImageView) findViewById(R.id.ivVolumeup);
        mVolumeMute = (ImageView) findViewById(R.id.ivVolumedown);
        mVolumeUp.setOnClickListener(this);
        mVolumeMute.setOnClickListener(this);
        vSeekbar.setProgress(86);
        vSeekbar.setOnSeekBarChangeListener(this);
        devOnOff = (ImageView) findViewById(R.id.ivonoff);
        devOnOff.setOnClickListener(this);
        checkInternet = new CheckInternet(HomeScreen.this);
        db = new DatabaseHelper(HomeScreen.this);
        getCmds = new ArrayList<GetCmds>();
        get_devices = new ArrayList<String>();
    //    get_devices_list = new ArrayList<String>();
        get_deviceCommand = new ArrayList<Devices>();
        get_deviceName = new ArrayList<Devices>();
        ipAddress = db.getAtlonaIp();

        get_deviceName = db.getHDMI();
        Log.d("Get Devicename-->", ""+get_deviceName.size());

        get_devices = db.getDevices();

        get_deviceCommand = db.getDeviceId();
        SERVER_IP = db.getAtlonaIp();
        SERVERPORT = Integer.parseInt("23");
        Log.d("Atlona IP-->", SERVER_IP);

        getPrefs = new GetPrefsValue(mContext);
        token = getPrefs.getPrefsValue("TOKEN");
        for(int i=0; i<get_deviceCommand.size(); i++){

            Devices devices = get_deviceCommand.get(i);


            String iodeviceId = devices.getInpDeviceId();
            String inpopType = devices.getInoutType();

            getDeviceCommands(token,iodeviceId,inpopType);
        }

        sToken = getPrefs.getPrefsValue("Token");
        mSettings.setOnClickListener(this);
        mHelp.setOnClickListener(this);
        devicesList();

        String atlonaCount = db.retAtlonacount();
        Log.d("No of Atlona Devices",atlonaCount);

//        connecttoTelnet();

        FragmentManager fragment = getSupportFragmentManager();
        FragmentTransaction transaction = fragment.beginTransaction();


        Intent intentt = getIntent();
        Bundle bundleOutput =intentt.getExtras();

        if(bundleOutput!=null) {
            String screenString = bundleOutput.getString("inputName");
            String inputPort = bundleOutput.getString("inputName");
            String outputPort = bundleOutput.getString("inputName");


         if(screenString == null) {

             HomeFragment fHomee = new HomeFragment();
             transaction.replace(R.id.containerLinear, fHomee);
             transaction.commit();
         }
           else if (screenString.equalsIgnoreCase("Television")) {

             ResultFragment fHomee = new ResultFragment();
             Bundle args = new Bundle();
             args.putString("STRING_I_NEED", getIntent().getExtras().getString("STRING_I_NEED"));
             fHomee.setArguments(args);
                transaction.replace(R.id.containerLinear, fHomee,"tag");
                transaction.commit();

            } else {

                HomeFragment fHomee = new HomeFragment();
                transaction.replace(R.id.containerLinear, fHomee);
                transaction.commit();

            }

        }

        else {

            Log.d("11","112");



        }



    }

    public void connecttoTelnet(){

        if(pioneer!=null && pioneer.isConnected())
            Log.d("Telnet","Connected");
        else {
            new AsyncTask<HomeScreen, Void, Void>() {
                @Override
                protected Void doInBackground(HomeScreen... act) {
                    try {
                        pioneer = new PioneerController(SERVER_IP, SERVERPORT, act[0]);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    Log.d("OnPostExecute","Telnet Connected");
                }
            }.execute(this);
        }
    }

    public void devicesList(){


        Collections.sort(get_deviceName);
        for(Devices devices : get_deviceName){
            String device_Name = devices.getDevName();
            String port_num = devices.getPortNum();
            String device_number = devices.getDeviceNumber();
            String inpopType = devices.getInoutType();
            int port = Integer.valueOf(port_num);
            if ((inpopType.equalsIgnoreCase("1")) ) {


                Log.d("device_number-->",""+device_number);
                if (device_Name.equalsIgnoreCase("Document Cam")) {
              String doc="Document Cam";
            addLayout(doc,port,device_number);


                } else if (device_Name.equalsIgnoreCase("Laptop")) {
                    String Lap="Laptop";
                    addLayout(Lap,port,device_number);

                } else if (device_Name.equalsIgnoreCase("Conference")) {
                    String con="Conference";
                    addLayout(con,port,device_number);

                } else if (device_Name.equalsIgnoreCase("Apple TV")) {
                    String val="Apple TV";
                    addLayout(val,port,device_number);

                } else if (device_Name.equalsIgnoreCase("Desktop")) {
                    String val="Desktop";
                    addLayout(val,port,device_number);

                } else if (device_Name.equalsIgnoreCase("DVD/Blue Ray")) {
                    String val="DVD/Blue Ray";
                    addLayout(val,port,device_number);

                }

                docConfigId = devices.getDevConfigId();
                docCurInp = devices.getCurInput();
                docDevid = devices.getInpDeviceId();
            }

        }
        }

    public void addLayout(String val,int port,String devNum) {
        LinearLayout innerLayout = new LinearLayout(this);

        LinearLayout inn = (LinearLayout) findViewById(R.id.controllinear);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(10, -20, 30, 0);

        innerLayout.setLayoutParams(params);
        ImageView imageview = new ImageView(this);

        if(val.equalsIgnoreCase("Document Cam")){
            imageview.setImageResource(R.mipmap.doccamera);}
        if(val.equalsIgnoreCase("Laptop")) {

            imageview.setImageResource(R.mipmap.laptop);}

        if(val.equalsIgnoreCase("Conference")) {

            imageview.setImageResource(R.mipmap.conference);}

        if(val.equalsIgnoreCase("Apple TV")) {
            imageview.setImageResource(R.mipmap.appletv);}

        if(val.equalsIgnoreCase("Desktop")) {
            imageview.setImageResource(R.mipmap.desktop);}

        if(val.equalsIgnoreCase("DVD/Blue Ray")) {
            imageview.setImageResource(R.mipmap.dvd);}


        innerLayout.addView(imageview);
        inn.addView(innerLayout);
          imageview.setOnClickListener(viewOnClickListener);
        imageview.setId(port);
        LinearLayout innerLayout1 = new LinearLayout(this);
        //innerLayout1.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        paramss.setMargins(-130, 125, 10, 0);
        innerLayout1.setLayoutParams(paramss);
        TextView txtvw = new TextView(this);
        txtvw.setText(devNum);
        txtvw.setId(port + 100);

        innerLayout1.addView(txtvw);
        innerLayout.addView(innerLayout1);

    }


    View.OnClickListener viewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int myId = v.getId();

            String port=String.valueOf(myId);
            get_devices_list=db.getDevicesName(port);
            Log.d("get_devices_list-->",""+get_devices_list);
            String inputName=get_devices_list;

            Log.e("dynamic",inputName);

            Intent intent = new Intent(HomeScreen.this, OutputScreen.class);               //TriggerCommandActivity.class);
            intent.putExtra("dynamicport", port);
            intent.putExtra("inputName", inputName);
            startActivity(intent);
        }

    };


    @Override
    public void onClick(View v) {
        if(v == mDesktop){
            if(deskCurInp != null && deskCurInp.length() != 0){
                new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",deskCurInp,"inp").execute();
            }
            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
//            sendCommand("x1AVx1");
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Computer fComputer = new Computer();
            transaction.replace(R.id.containerLinear,fComputer);
            transaction.commit();
        }else if(v == mLaptop){
            if(lapCurInp != null && lapCurInp.length() != 0){
                new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",lapCurInp,"inp").execute();
            }
            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
//            sendCommand("x4AVx1");
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Laptop fLaptop = new Laptop();
            transaction.replace(R.id.containerLinear,fLaptop);
            transaction.commit();
        }else if(v == mDoccam){
            if(docCurInp != null && docCurInp.length() != 0){
                new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",docCurInp,"inp").execute();
            }
            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
//            sendCommand("x3AVx1");
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Doccam fDoccam = new Doccam();
            transaction.replace(R.id.containerLinear,fDoccam);
            transaction.commit();
        }else if(v == mDvd){
            if(dvdCurInp != null && dvdCurInp.length() != 0){
                new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",dvdCurInp,"inp").execute();
            }
            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
//            sendCommand("x2AVx1");
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Dvdbluray fDvdbluray = new Dvdbluray();
            transaction.replace(R.id.containerLinear,fDvdbluray);
            transaction.commit();
        }
       else if(v == mAppletv){
            if(appletvCurInp != null && appletvCurInp.length() != 0){
                new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",deskCurInp,"inp").execute();
            }


            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Appletv fAppletv = new Appletv();
            transaction.replace(R.id.containerLinear,fAppletv);
            transaction.commit();
        }else if(v == mSettings){

            Log.e("settings","called");
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            SettingsFragment settings = new SettingsFragment();
            transaction.replace(R.id.containerLinear,settings);
            transaction.commit();
        }else if(v == mHelp){
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            HelpFragment help = new HelpFragment();
            transaction.replace(R.id.containerLinear,help);
            transaction.commit();
        }else if(v == mleftArrow){
            hScroll.setScrollX(0);
        }else if(v == mrightArrow){
            hScroll.setScrollX(hScroll.getRight());
        }else if(v == mBluray){
            Intent intent = new Intent(HomeScreen.this,OutputScreen.class);
            startActivity(intent);
            FragmentManager fragment = getSupportFragmentManager();
            FragmentTransaction transaction = fragment.beginTransaction();
            Bluray bluray = new Bluray();
            transaction.replace(R.id.containerLinear,bluray);
            transaction.commit();
        }
    else if(v == devOnOff){
            Log.e("homescreen","PWROFFF");
            if(pioneer != null && pioneer.isConnected()){
                if(powFlag){
                    sendCommand("PWON");
                    powFlag = false;
                }else{
                    sendCommand("PWOFF");
                    Log.e("homescreen","PWROF");
                    powFlag = true;
                }
            }else{
                connecttoTelnet();
            }
        }else if(v == mVolumeUp){
            new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","6","volplus").execute();
        }else if(v == mVolumeMute){
            new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","-80","volminus").execute();
        }

    }

    public void getDeviceCommands(final String sToken, final String deviceId, final String isInp){
        String cmdurl = "http://ec2-52-38-248-216.us-west-2.compute.amazonaws.com:1337/MobileSync/getCommands";
        StringRequest strRequest = new StringRequest(Request.Method.POST, cmdurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0; i<jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        if(jsonObject.has("InDeviceID")){
                            Log.d("IndeviceId-->",""+jsonObject);
                            InputDeviceCommands inpDevCommands = gson.fromJson(jsonObject.toString(),InputDeviceCommands.class);
                            db.addInputCommands(inpDevCommands);
                        }else{
                            Log.d("OutdeviceId-->",""+jsonObject);
                            OutputDeviceCommands outDevCommands = gson.fromJson(jsonObject.toString(),OutputDeviceCommands.class);
                            Log.d("outDevCommands",outDevCommands.getOutDeviceId());
                            db.addOutputCommands(outDevCommands);
                        }

                    }
                }catch(Exception e){

                }

                Log.d("GetCommandResponse-->",response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("APIKEY", "542f0190-ad6f-11e5-93d5-039280b0a9db");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", sToken);
                params.put("deviceid", deviceId);
                params.put("isinput", isInp);

                return params;
            }
        };

        RequestQueue requeue = Volley.newRequestQueue(HomeScreen.this);
        requeue.add(strRequest);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        Log.e("CurrentVolume----->",""+String.valueOf(seekBar.getProgress()));
       getPrefs.setPrefs("Volume",String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String curVol = getPrefs.getPrefsValue("Volume");
        Toast.makeText(getApplicationContext(),"Volume-->"+curVol,Toast.LENGTH_LONG).show();
        vSeekbar.setProgressAndThumb( Integer.parseInt(curVol) );

        int volume = Integer.parseInt(curVol)-80;
        if(volume > 6){
            volume = 6;
        }
        Log.d("CurrentVolumee-->",""+volume);
        new GetsessionIdOne("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",String.valueOf(volume)).execute();
    }

    /**
     * AlertDialog that will display the error code and the corresponding error Message.
     * This will be called in the onPostExecute of LoginBgProcess.
     * @param rCode
     * @param eMsg
     */

    private void errorMessage(final String rCode, final String eMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Response Code: "+rCode);
        builder.setMessage(eMsg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {

                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    public static int moreThanOnce(ArrayList<String> list, String searched) {

        int numCount = 0;
        boolean more = false;

        for (String thisNum : list) {
            if (thisNum.equalsIgnoreCase(searched)) {
                numCount ++ ;
            }
        }

        if (numCount > 1) {
            more = true;
        }

        return numCount;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(HomeScreen.this, GetSubsites.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Site_ID",siteId);
        startActivity(intent);
        HomeScreen.this.finish();
    }

    public void sendCommand(final String commands)
    {
        appendToConsole("Command Sent: " + commands);
        Log.d("Commands:", "Value: " + commands);

        new AsyncTask<HomeScreen, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(HomeScreen... act) {
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
                    Log.d("OnPostExecute","SendCommand");
                }
            }
        }.execute(this);
    }


    public void appendToConsole(String str) {
        inputStatus.append("\n");
        inputStatus.append(str);
    }

    public void setStatus(String s) {
        outputStatus.setText(s);
    }

    public class GetsessionId extends AsyncTask<String, String, String>{

        String url, sessionId, input, cmdtype;

        public GetsessionId(String url, String input, String cmdtype){
            this.url = url;
            this.input = input;
            this.cmdtype = cmdtype;
        }

        @Override
        protected String doInBackground(String... params) {

            try{
                Getuid getUid = new Getuid();
                sessionId = getUid.makeHttpRequest(url);
                return sessionId;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if(str != null){
                String[] temp = str.split(";");
                if(cmdtype.equalsIgnoreCase("inp")){
                    String inp = input.substring(4);
                    String inpurl = "http://"+ipAddress+"/ajstatus.html?value=status&uid="+temp[1]+"&mlf=1&inp="+inp;
                    new SwitchDevice(inpurl).execute();
                }else if(cmdtype.equalsIgnoreCase("volplus")){
                    String volurl = "http://"+ipAddress+"/ajstatus.html?value=status&uid="+temp[1]+"&mlf=1&vol="+input;
                    new Changevolume(volurl).execute();
                }else if(cmdtype.equalsIgnoreCase("volminus")){
                    String volurl = "http://"+ipAddress+"/ajstatus.html?value=status&uid="+temp[1]+"&mlf=1&vol="+input;
                    new Changevolume(volurl).execute();
                }
            }

        }
    }


    public class GetsessionIdOne extends AsyncTask<String, String, String>{

        String url, sessionId, input;

        public GetsessionIdOne(String url, String input){
            this.url = url;
            this.input = input;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Getuid getUid = new Getuid();
                sessionId = getUid.makeHttpRequest(url);
                return sessionId;
            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if(str != null){
                String[] temp = str.split(";");
                String volurl = "http://"+ipAddress+"/ajstatus.html?value=status&uid="+temp[1]+"&mlf=1&vol="+input;
                new Changevolume(volurl).execute();
            }

        }
    }


    public class Changevolume extends AsyncTask<String,String,String>{

        String volurl;

        public Changevolume(String volurl){
            this.volurl = volurl;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Getuid getuid = new Getuid();
                getuid.makeHttpRequest(volurl);
                return "success";
            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Change Volume","Success");
        }
    }

    public class SwitchDevice extends AsyncTask<String, String, String>{

        String url;

        public SwitchDevice(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Getuid getuid = new Getuid();
                getuid.makeHttpRequest(url);
                return "success";
            }catch (Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("Switch Command","Success");
        }
    }
}