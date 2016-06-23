package com.homemonitoring;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.utils.CheckInternet;
import com.utils.DatabaseHelper;
import com.utils.GetPrefsValue;
import com.utils.Sites;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {

    EditText userName, passWord;
    Button login;
    String loginurl,apikey, uName, pWord, siteUrl;
    CheckInternet checkInternet;
    ProgressDialog mProgress;
    DatabaseHelper dbase;
    GetPrefsValue getPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = (EditText) findViewById(R.id.etUsername);
        passWord = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btLogin);
        login.setOnClickListener(this);
        dbase = new DatabaseHelper(LoginActivity.this);
        getPrefs = new GetPrefsValue(LoginActivity.this);
        loginurl = "http://ec2-52-38-248-216.us-west-2.compute.amazonaws.com:1337/MobileSync/Login";
        siteUrl = "http://ec2-52-38-248-216.us-west-2.compute.amazonaws.com:1337/MobileSync/getSites";
        apikey = "542f0190-ad6f-11e5-93d5-039280b0a9db";
        checkInternet = new CheckInternet(this);
        mProgress = new ProgressDialog(LoginActivity.this);
        mProgress.setCancelable(false);
        mProgress.setMessage("Loading. Please wait...");


    }

    @Override
    public void onClick(View v) {

        if (v == login){
            getJsonResponse();
        }

    }

    public void getJsonResponse(){
        showProgressDialog();
        uName = userName.getText().toString().trim();
        pWord = passWord.getText().toString().trim();
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", uName);
        params.put("password", pWord);

        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, loginurl, new JSONObject(params),
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    String cusId = jsonObject.getString("customerid");
                    String udid = jsonObject.getString("UDID");
                    String siteId = jsonObject.getString("siteID");
                    String userName = jsonObject.getString("username");
                    String passWord = jsonObject.getString("password");
                    String token = jsonObject.getString("token");
                    String status = jsonObject.getString("status");
                    if(status.equalsIgnoreCase("Active")){
                        getPrefs.setPrefs("TOKEN",token);
                        getPrefs.setPrefs("CUSTID",cusId);
                        getStringResponse(token,cusId);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d("JsonResponse",""+jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(volleyError instanceof NoConnectionError){

                }
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("APIKEY", "542f0190-ad6f-11e5-93d5-039280b0a9db");
                return headers;
            }
        };

        RequestQueue reqQueue = Volley.newRequestQueue(LoginActivity.this);
        reqQueue.add(jsonObject);
    }


    /**
     * AlertDialog that will display the error code and the corresponding error Message.
     * This will be called in the onPostExecute of LoginBgProcess.
     * @param rCode
     * @param eMsg
     */

    private void errorMessage(final String rCode, final String eMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(rCode);
        builder.setMessage(eMsg)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {

                    }
                });

        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void showProgressDialog() {
        if (!mProgress.isShowing())
            mProgress.show();
    }

    private void hideProgressDialog() {
        if (mProgress.isShowing())
            mProgress.hide();
    }


    private void getStringResponse(final String token, final String custId){
        StringRequest strRequest = new StringRequest(Request.Method.POST, siteUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                Log.d("JsonArray", ""+str);
                try{
                    JSONArray jArray = new JSONArray(str);
                    for(int i=0; i<jArray.length(); i++){
                        JSONObject jObject = jArray.getJSONObject(i);
                        Gson gson = new Gson();
                        Sites sites = gson.fromJson(jObject.toString(),Sites.class);
                        dbase.addSites(sites.getSiteId(), sites.getUserId(), sites.getSiteName(), sites.getDeviceId(),
                                sites.getmIp(), sites.getIsSubsites(), sites.getClientId(), sites.getCustomerId());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                hideProgressDialog();
                Intent intent = new Intent(LoginActivity.this,GetSites.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                LoginActivity.this.finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

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
                params.put("token", token);
                params.put("customerid", custId);
                return params;
            }
        };

        RequestQueue requeue = Volley.newRequestQueue(LoginActivity.this);
        requeue.add(strRequest);
    }

}

