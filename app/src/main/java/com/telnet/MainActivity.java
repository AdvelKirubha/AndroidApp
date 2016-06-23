package com.telnet;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.homemonitoring.R;

import java.io.IOException;

public class MainActivity extends Activity {

    private static final int MIN_VOL = 0;
    private static final int MAX_VOL = 80;
    private static TextView inputStatus;
    private static TextView outputStatus;
    private static NumberPicker numpicker;
    private static int SERVERPORT = 23;
    private static String SERVER_IP = "192.168.0.105";
    String commands;
    EditText et;
    private PioneerController client = null;

   /* public void onVolumeChange(View view) {
        if (!client.isConnected()) {
            toastFast("Not connected to a server");
            return;
        }

        client.changeVolume(numpicker.getValue());
        //client.getVolume();
    }*/
    private Toast fastToast;

    @SuppressLint("ShowToast")
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EditText etIp = (EditText) findViewById(R.id.EditTextIp);
        inputStatus = (TextView) findViewById(R.id.inputStreamTextView);
        outputStatus = (TextView) findViewById(R.id.statusStreamTextView);
        inputStatus.setMovementMethod(new ScrollingMovementMethod());
        outputStatus.setMovementMethod(new ScrollingMovementMethod());

        NumberPicker num = (NumberPicker) findViewById(R.id.volumePicker);
        numpicker = num;
        numpicker.setMinValue(MIN_VOL);
        numpicker.setMaxValue(MAX_VOL);

        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        fastToast = Toast.makeText(this, "", Toast.LENGTH_LONG);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        if (sharedPref.contains("last_server"))
            etIp.setText(sharedPref.getString("last_server", ""));
    }

    @Override
    protected void onStop() {
        if (client != null && client.isConnected()) {
            if (disconnect()) toastFast("Disconnected from server");
            else toastFast("Error disconnecting from server");
        }
        super.onStop();
    }

    void toastFast(String str) {
        fastToast.setText(str);
        fastToast.show();
    }

    private boolean etIsEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean disconnect() {
        if (client.disconnect()) {
            //setPower(false);
            return true;
        }
        return false;
    }

    public void onClickConnect(View view) {
        EditText etIp = (EditText) findViewById(R.id.EditTextIp);
        //EditText etPort = (EditText) findViewById(R.id.EditTextPort);

        if (!etIsEmpty(etIp)) {
            String tmp = etIp.getText().toString();

            if (tmp.contains(":")) {
                String[] address = tmp.split(":");
                SERVER_IP = address[0];
                SERVERPORT = Integer.parseInt(address[1]);
            } else {
                SERVER_IP = etIp.getText().toString();
            }

            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("last_server", tmp);
            editor.apply();
        } else
            toastFast("Enter a server IP");

        if (client != null && client.isConnected())
            toastFast("Already connected");
        else {
            new AsyncTask<MainActivity, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(MainActivity... act) {
                    Boolean flag = false;
                    /*try {
                        client = new PioneerController(SERVER_IP, SERVERPORT, act[0]);
                        flag = true;
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        toastFast("Error: " + e.getMessage());
                    } catch (Exception e) {
                        toastFast("Error: " + e.getMessage());
                    }*/
                    return flag;
                }

                @Override
                protected void onPostExecute(Boolean flag) {
                    //numpicker.setValue(client.getVolume());
                    if (flag) {
                        if (client != null && client.isConnected())
                            toastFast("Connected");
                    }
                }
            }.execute(this);
        }
    }

    public void setPower(boolean on) {
        ToggleButton power = (ToggleButton) findViewById(R.id.powerButton);
        power.setChecked(on);
    }

    public void onClickDisconnect(View view) {
        if (client != null && client.isConnected()) {
            if (disconnect()) {
                toastFast("Disconnected from server");
            } else toastFast("Error disconnecting from server");
        } else {
            toastFast("Already disconnected");
        }
    }

    public void onClickSend(View view) {
        if (client == null || !client.isConnected()) {
            toastFast("Not connected to a server");
            return;
        }

        et = (EditText) findViewById(R.id.EditTextCommand);
        commands = et.getText().toString();
        if (commands.length() < 1) {
            toastFast("Please enter the command... ");
            return;
        } else {
            commands += "\r\n";
            sendCommand(commands);
        }
    }

    public void sendCommand(final String commands)
    {
        appendToConsole("Command Sent: " + commands);
        Log.d("Commands:", "Value: " + commands);

        new AsyncTask<MainActivity, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(MainActivity... act) {
                Boolean flag = false;
                try {
                    client.sendCommand(commands);
                    flag=true;
                } catch (Exception e) {
                    toastFast("Error: " + e.getMessage());
                }
                return flag;
            }

            @Override
            protected void onPostExecute(Boolean flag) {
                if (flag) {
                    toastFast("Command Sent Successfully");
                    et.setText("");
                }
            }
        }.execute(this);
    }


    public void onClickPower(View view) {
        if (client == null || !client.isConnected()) {
            toastFast("Not connected to a server");
            return;
        }
        //client.togglePower();
    }

    public void onClickPlay(View view) {
        //client.play();
    }

    public void onClickStop(View view) {
        // client.stop();
    }

    public void onClickXbox(View view) {
        // client.input("Xbox");
    }

    public void onClickProjector(View view) {
        //client.input("Projector");
    }

    public void onClickRP(View view) {
        //client.input("Radio Paradise");
    }

    public void appendToConsole(String str) {
        inputStatus.append("\n");
        inputStatus.append(str);
    }

    public void resetConsole() {
        inputStatus.setText("");
    }

    public String getConsole() {
        return inputStatus.getText().toString();
    }

    public void setConsole(String str) {
        inputStatus.setText(str);
    }

    public void setVolume(int volume) {
        if (!numpicker.isDirty())
            numpicker.setValue(volume);
    }

    public void setStatus(String s) {
        outputStatus.setText(s);
    }
}