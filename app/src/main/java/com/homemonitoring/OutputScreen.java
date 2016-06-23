package com.homemonitoring;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.utils.DatabaseHelper;
import com.utils.Getuid;
import com.utils.OutputDeviceCommands;

import java.math.BigInteger;
import java.util.ArrayList;

public class OutputScreen extends Activity {

    LinearLayout outer,outer0,outer1;
    RelativeLayout aa;
    DatabaseHelper dbase;
    String deviceId, powerOn, powerOff, volPlus, volMinus, muteOn;
    ArrayList<String> outputDevices;
    ArrayList<String> outputDevicesName;
    ArrayList<OutputDeviceCommands> getoutCmds;
    String[] cmdType, cmdValue ;
    String ipaddrs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outputscreen);

        outer = (LinearLayout) findViewById(R.id.outLayout1);
        outer1 = (LinearLayout) findViewById(R.id.outLayout0);
        //aa=(RelativeLayout)findViewById(R.id.aaa);
        dbase = new DatabaseHelper(this);
        outputDevices = new ArrayList<String>();
        outputDevicesName = new ArrayList<String>();
        cmdType = new String[16];
        cmdValue = new String[16];
        getoutCmds = new ArrayList<OutputDeviceCommands>();
        outputDevices = dbase.getOutPutDevices();
        outputDevicesName = dbase.getOutPutDevicesName();

        ipaddrs = dbase.getAtlonaIp();

        for (int i=0;i<outputDevices.size();i++) {
            String outDev = outputDevices.get(i);
            //String outDev = outputDevices.get(i);
            Log.e("OutputDevices", outDev);
           // Log.e("OutputDevices", outDev);
        }
        /*
        for (int i=0;i<outputDevices.size();i++){
            String outDev = outputDevices.get(i);
            Log.d("OutputDevices",outDev);
            getoutCmds = dbase.getOutDeviceCommands(outDev);
        }


        *//*if(!getoutCmds.isEmpty()){
            OutputDeviceCommands commands = getoutCmds.get(0);
            powerOn = commands.getPowerOn();
            powerOff = commands.getPowerOff();
            volPlus = commands.getVolumePlus();
            volMinus = commands.getVolumeMinus();
            muteOn = commands.getMuteOn();
        }*//*


       for(int i=0; i<getoutCmds.size(); i++){
            OutputDeviceCommands commands = getoutCmds.get(i);
            powerOn = commands.getPowerOn();
           Log.d("POWERON",powerOn);
            powerOff = commands.getPowerOff();
            volPlus = commands.getVolumePlus();
            volMinus = commands.getVolumeMinus();
            muteOn = commands.getMuteOn();
        }*/

        int noDevices = Integer.parseInt(dbase.getNoOfoutputDevices());

        commandType();
        commandvalue();

     //   RelativeLayout innerLayouts=new RelativeLayout(this);
        for (int i=0; i<noDevices; i++) {




//            ImageView imageview = new ImageView(this);
//            //imageview.setId(R.id.imageid);
//           imageview.setId(i);
//            imageview.setImageResource(R.mipmap.desktop);
//
//
//                        TextView txtvw = new TextView(this);
//            txtvw.setText("Channel-"  + i);
//            txtvw.setId(i + 1);
//
//            RelativeLayout.LayoutParams imageBtnlay=new RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//            RelativeLayout.LayoutParams textvwlay=new RelativeLayout.LayoutParams(
//                    RelativeLayout.LayoutParams.MATCH_PARENT,
//                    RelativeLayout.LayoutParams.MATCH_PARENT);
//
//
//            textvwlay.addRule(RelativeLayout.ABOVE,imageview.getId());
//            textvwlay.setMargins(10,10,10,10);
//
//
//                innerLayouts.addView(imageview,imageBtnlay);
//                innerLayouts.addView(txtvw,textvwlay);
//    aa.addView(innerLayouts);
//
//            //    innerLayout.addView(imageview);
//
















            LinearLayout innerLayout = new LinearLayout(this);
            innerLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 10, 40, 10);

            innerLayout.setLayoutParams(params);
            ImageView imageview = new ImageView(this);
          //  imageview.setId(R.id.imageid);
            imageview.setId(i+1);
            imageview.setImageResource(R.mipmap.desktop);
            innerLayout.addView(imageview);
            outer.addView(innerLayout);
            imageview.setOnClickListener(viewOnClickListener);

            LinearLayout innerLayout1 = new LinearLayout(this);
            innerLayout1.setOrientation(LinearLayout.VERTICAL);
            LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            paramss.setMargins(75, 10,0, 10);
            innerLayout1.setLayoutParams(paramss);
            TextView txtvw = new TextView(this);

            for (int check=i;check==i;check++){
                String outDev = outputDevicesName.get(i);
                Log.e("OutputDevices", outDev);

                //if(outDev.length()<5){
                txtvw.setText(outDev);
            }
            //txtvw.setText("Channel-"  + i);
            txtvw.setId(i + 5);

            innerLayout1.addView(txtvw);

           outer1.addView(innerLayout1);
        }





        /*for(int i=0; i<noDevices; i++) {
            LinearLayout innerLayout1 = new LinearLayout(this);
            innerLayout1.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams paramss = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

                paramss.setMargins(80, 10, 40, 10);


            innerLayout1.setLayoutParams(paramss);


            TextView tv = new TextView(this);
            tv.setText("PC" +
                    "" + i);
            tv.setId(i + 5);
            innerLayout1.addView(tv);
            outer1.addView(innerLayout1);

        }
*/



    }


    View.OnClickListener viewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int outputPort = v.getId();


            Intent intentt = getIntent();
            Bundle homeBundle =intentt.getExtras();

            if(homeBundle!=null) {
                String port = homeBundle.getString("dynamicport");
                String outputName = homeBundle.getString("inputName");


                Intent intent = new Intent(OutputScreen.this, HomeScreen.class);               //TriggerCommandActivity.class);
                //String strName = inputNamee;
                intent.putExtra("inputPort", port);
                intent.putExtra("outputPort", outputPort);
                intent.putExtra("inputName", outputName);

                startActivity(intent);
                OutputScreen.this.finish();
            }
//            new GetsessionId("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","uron","POWR1").execute();

            /*for(int i=0; i<cmdType.length;i++){
                String command = cmdType[i];
                Log.d("CommandType",command);
                String value = cmdValue[i];
                Log.d("Value",value);
                new GetsessionId("http://192.168.1.2/ajlogin.html?value=login&usn=root&pwd=Atlona",command,value).execute();
            }*/

        }
    };

    public String getAscii(String str){
        String ascii = "";
        StringBuilder sb= new StringBuilder();
        for (int i= 0; i < str.length(); i++) {
            sb.append((int) str.charAt(i));
        }
        ascii = sb.toString();
        return ascii;
    }

/*
    public class GetsessionId extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionId(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommands(seturl).execute();
        }
    }

    public class GetsessionIdOne extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdOne(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsOne(seturl).execute();
        }
    }

    public class GetsessionIdTwo extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdTwo(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommands(seturl).execute();
        }
    }

    public class GetsessionIdThree extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdThree(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsTwo(seturl).execute();
        }
    }

    public class GetsessionIdFour extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdFour(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommands(seturl).execute();
        }
    }

    public class GetsessionIdFive extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdFive(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://192.168.1.2/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommands(seturl).execute();
        }
    }

    public class SetCommands extends AsyncTask<String, String, String>{

        String url;

        public SetCommands(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdOne("http://192.168.1.2/ajlogin.html?value=login&usn=root&pwd=Atlona","euron","3").execute();

        }
    }

    public class SetCommandsOne extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsOne(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdTwo("http://192.168.1.2/ajlogin.html?value=login&usn=root&pwd=Atlona","fbon","POWR1").execute();
        }
    }

    public class SetCommandsTwo extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsTwo(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdThree("http://192.168.1.2/ajlogin.html?value=login&usn=root&pwd=Atlona","fbon","POWR1").execute();
        }
    }
*/













//************************************************************


    public class GetsessionId extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionId(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommands(seturl).execute();
        }
    }

    public class SetCommands extends AsyncTask<String, String, String>{

        String url;

        public SetCommands(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            Intent intent = new Intent(OutputScreen.this,TriggerCommandActivity.class);
            startActivity(intent);
            new GetsessionIdOne("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","euron","3").execute();

        }
    }


    public class GetsessionIdOne extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdOne(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsOne(seturl).execute();
        }
    }

    public class SetCommandsOne extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsOne(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
//            new GetsessionIdTwo("http://192.168.1.2/ajlogin.html?value=login&usn=root&pwd=Atlona","fbon","POWR1").execute();

        }
    }


    public class GetsessionIdTwo extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdTwo(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsTwo(seturl).execute();
        }
    }

    public class SetCommandsTwo extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsTwo(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdThree("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","efbon","3").execute();

        }
    }


    public class GetsessionIdThree extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdThree(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsThree(seturl).execute();
        }
    }

    public class SetCommandsThree extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsThree(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdFour("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","uroff","POWR0").execute();

        }
    }



    public class GetsessionIdFour extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdFour(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsFour(seturl).execute();
        }
    }

    public class SetCommandsFour extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsFour(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdFive("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","euroff","3").execute();

        }
    }



    public class GetsessionIdFive extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdFive(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsFive(seturl).execute();
        }
    }

    public class SetCommandsFive extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsFive(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdsix("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","fboff","POWR0").execute();

        }
    }




    public class GetsessionIdsix extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdsix(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandssix(seturl).execute();
        }
    }

    public class SetCommandssix extends AsyncTask<String, String, String>{

        String url;

        public SetCommandssix(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdseven("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","efboff","3").execute();

        }
    }



    public class GetsessionIdseven extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdseven(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsseven(seturl).execute();
        }
    }

    public class SetCommandsseven extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsseven(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdeight("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","urvoladd","VOLM").execute();

        }
    }




    public class GetsessionIdeight extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdeight(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandseight(seturl).execute();
        }
    }

    public class SetCommandseight extends AsyncTask<String, String, String>{

        String url;

        public SetCommandseight(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdnine("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","eurvoladd","3").execute();

        }
    }


    public class GetsessionIdnine extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdnine(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsnine(seturl).execute();
        }
    }

    public class SetCommandsnine extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsnine(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdten("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","urvolsub","VOLM").execute();

        }
    }



    public class GetsessionIdten extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdten(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsten(seturl).execute();
        }
    }

    public class SetCommandsten extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsten(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdeleven("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","eurvolsub","3").execute();

        }
    }


    public class GetsessionIdeleven extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdeleven(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandseleven(seturl).execute();
        }
    }

    public class SetCommandseleven extends AsyncTask<String, String, String>{

        String url;

        public SetCommandseleven(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdtwelve("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","urmute","MUTE1").execute();

        }
    }



    public class GetsessionIdtwelve extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdtwelve(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandstwelve(seturl).execute();
        }
    }

    public class SetCommandstwelve extends AsyncTask<String, String, String>{

        String url;

        public SetCommandstwelve(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdthirteen("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","eurmute","3").execute();

        }
    }


    public class GetsessionIdthirteen extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdthirteen(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsthirteen(seturl).execute();
        }
    }

    public class SetCommandsthirteen extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsthirteen(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdfourteen("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","fbmute","MUTE1").execute();

        }
    }



    public class GetsessionIdfourteen extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdfourteen(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsfourteen(seturl).execute();
        }
    }

    public class SetCommandsfourteen extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsfourteen(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
            new GetsessionIdFifteen("http://"+ipaddrs+"/ajlogin.html?value=login&usn=root&pwd=Atlona","efbmute","3").execute();

        }
    }



    public class GetsessionIdFifteen extends AsyncTask<String, String, String> {

        String url, sessionId, cmdVal, cmdType, aValue;

        public GetsessionIdFifteen(String url, String cmdType, String cmdVal){
            this.url = url;
            this.cmdVal = cmdVal;
            this.cmdType = cmdType;
        }

        @Override
        protected String doInBackground(String... params) {
            Log.d("DoinBack","Back");
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(cmdVal.length() > 1){
                aValue = toHex(cmdVal);
            }
            Log.d("UIDSTR",temp[1]);
            String seturl = "http://"+ipaddrs+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&"+cmdType+"="+aValue;
            new SetCommandsfifteen(seturl).execute();
        }
    }

    public class SetCommandsfifteen extends AsyncTask<String, String, String>{

        String url;

        public SetCommandsfifteen(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            String response = getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand--->", "Success");
            Intent intent = new Intent(OutputScreen.this,TriggerCommandActivity.class);
            startActivity(intent);

        }
    }

//    ********************************************************************//










    public void commandType(){
        cmdType[0] = "uron";
        cmdType[1] = "euron";
        cmdType[2] = "fbon";
        cmdType[3] = "efbon";
        cmdType[4] = "uroff";
        cmdType[5] = "euroff";
        cmdType[6] = "fboff";
        cmdType[7] = "efboff";
        cmdType[8] = "urvoladd";
        cmdType[9] = "eurvoladd";
        cmdType[10] = "urvolsub";
        cmdType[11] = "eurvolsub";
        cmdType[12] = "urmute";
        cmdType[13] = "eurmute";
        cmdType[14] = "fbmute";
        cmdType[15] = "efbmute";

    }

    public void commandvalue(){
        cmdValue[0] = "POWR1";
        cmdValue[1] = "3";
        cmdValue[2] = "POWR1";
        cmdValue[3] = "3";
        cmdValue[4] = "POWR0";
        cmdValue[5] = "3";
        cmdValue[6] = "POWR0";
        cmdValue[7] = "3";
        cmdValue[8] = "VOLM";
        cmdValue[9] = "3";
        cmdValue[10] = "VOLM";
        cmdValue[11] = "3";
        cmdValue[12] = "MUTE1";
        cmdValue[13] = "3";
        cmdValue[14] = "MUTE1";
        cmdValue[15] = "3";
    }

    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }

}
