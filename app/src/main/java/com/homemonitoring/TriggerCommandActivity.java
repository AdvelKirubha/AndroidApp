package com.homemonitoring;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.utils.DatabaseHelper;
import com.utils.GetPrefsValue;
import com.utils.Getuid;
import com.utils.VerticalSeekBar;

import java.math.BigInteger;

public class TriggerCommandActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {

    Button on, off;
    VerticalSeekBar sBar;
    GetPrefsValue getPrefs;
    DatabaseHelper dbase;
    String ipAddress;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trigger_command);

        getPrefs = new GetPrefsValue(TriggerCommandActivity.this);
        on = (Button) findViewById(R.id.button);
        on.setOnClickListener(this);
        off = (Button) findViewById(R.id.button2);
        off.setOnClickListener(this);
        dbase = new DatabaseHelper(this);
        ipAddress = dbase.getAtlonaIp();
        sBar = (VerticalSeekBar) findViewById(R.id.seekBar1);
        sBar.setMax(100);
        sBar.setOnSeekBarChangeListener(this);
        image = (ImageView) findViewById(R.id.imageView20);
        image.setOnClickListener(this);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        getPrefs.setPrefs("OutputVolume",String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        String volume = "";
        String curVol = getPrefs.getPrefsValue("OutputVolume");
        Toast.makeText(TriggerCommandActivity.this, curVol, Toast.LENGTH_SHORT).show();
        sBar.setProgressAndThumb( Integer.parseInt(curVol) );

       if(curVol.length()==2){
            volume = "0"+curVol;
        }else if(curVol.length() == 1){
            volume = "00"+curVol;
        }else{
            volume = curVol;
        }

        new GetsessionId("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","VOLM"+volume).execute();

    }

    @Override
    public void onClick(View v) {
        if(v == on){
            new GetsessionIdTwo("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","On").execute();
        }else if(v == off){
            new GetsessionIdTwo("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","Off").execute();
        }else if(v == image){
            new GetsessionIdTwo("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona","Mute").execute();
        }
    }

    public class GetsessionId extends AsyncTask<String, String, String> {

        String url, volume, sessionId;

        public GetsessionId(String url, String volume){
            this.url = url;
            this.volume = volume;

        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Log.d("Started","--->");
                Getuid getUid = new Getuid();
                sessionId = getUid.makeHttpRequest(url);
                return sessionId;

            }catch(Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if(str!= null){
                String[] temp = str.split(";");
                Log.d("Length",""+volume.length());
                String asciiVal = toHex(volume);
                String url = "http://"+ipAddress+"/ajcontrol.html?value=control&uid="+temp[1]+"&mlf=1&urvoladd="+asciiVal;
                new SetCommands(url).execute();
            }

        }
    }

    public class SetCommands extends AsyncTask<String, String, String>{

        String url;

        public SetCommands(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                Getuid getUid = new Getuid();
                getUid.makeHttpRequest(url);
                return "success";
            }catch(Exception e){
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand-->", "Success");

            new GetsessionIdOne("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona").execute();
        }
    }


    public class GetsessionIdOne extends AsyncTask<String, String, String> {

        String url, sessionId;

        public GetsessionIdOne(String url){
            this.url = url;

        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            String url = "http://"+ipAddress+"/ajstatus.html?value=control&uid="+temp[1]+"&mlf=1&trsvol=1";
            new TrigCommands(url).execute();
        }
    }

    public class GetsessionIdTwo extends AsyncTask<String, String, String> {

        String url, sessionId, type, surl;

        public GetsessionIdTwo(String url, String type){
            this.url = url;
            this.type = type;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            sessionId = getUid.makeHttpRequest(url);
            return sessionId;
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            String[] temp = str.split(";");
            if(type.equalsIgnoreCase("Off")){
                String hex = toHex("POWR0");
                surl  = "http://"+ipAddress+"/ajstatus.html?value=control&uid="+temp[1]+"&mlf=1&trspw=0,"+hex+",3";
            }else if(type.equalsIgnoreCase("On")){
                String hex = toHex("POWR1");
                surl = "http://"+ipAddress+"/ajstatus.html?value=control&uid="+temp[1]+"&mlf=1&trspw=1,"+hex+",3";
            }else if(type.equalsIgnoreCase("Mute")){
                String hex = toHex("MUTE");
                surl = "http://192.168.1.2/ajstatus.html?value=control&uid="+temp[1]+"&mlf=1&trsmute=2,"+hex+",3";
            }

            new TrigCommands(surl).execute();
        }
    }


    public class TrigCommands extends AsyncTask<String, String, String>{

        String url;

        public TrigCommands(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... params) {
            Getuid getUid = new Getuid();
            getUid.makeHttpRequest(url);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("SetCommand", "Success");
        }
    }

    public String getAscii(String str){
        String ascii = "";
        StringBuilder sb= new StringBuilder();
        for (int i= 0; i < str.length(); i++) {
            sb.append((int) str.charAt(i));
        }
        ascii = sb.toString();
        return ascii;
    }

    public String toHex(String arg) {
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }
}
