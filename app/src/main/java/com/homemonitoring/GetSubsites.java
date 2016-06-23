package com.homemonitoring;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.utils.AtlonaDevices;
import com.utils.DatabaseHelper;
import com.utils.Devices;
import com.utils.GetPrefsValue;
import com.utils.ListAdapter;
import com.utils.Subsites;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSubsites extends AppCompatActivity {

    List<String> mData;
    List<String> mId;
    ListAdapter mAdapter;
    DatabaseHelper dbase;
    Context mContext;
    ListView mList;
    String token, isSubsite, siteId;
    GetPrefsValue getPrefs;
    ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getsubsites);

        Intent intent = getIntent();
        siteId = intent.getStringExtra("Site_ID");
        mContext = GetSubsites.this;
        dbase = new DatabaseHelper(mContext);
        mList = (ListView) findViewById(R.id.subsiteList);
        mData = new ArrayList<String>();
        mId = new ArrayList<String>();
        mProgress = new ProgressDialog(mContext);
        mProgress.setCancelable(false);
        mProgress.setMessage("Loading. Please wait...");
        getPrefs = new GetPrefsValue(mContext);
        token = getPrefs.getPrefsValue("TOKEN");
        getSubsiteList();
        dbase.clearDevices();

        mAdapter = new ListAdapter(mContext,mData,mId);
        mList.setAdapter(mAdapter);

        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tView = (TextView) view.findViewById(R.id.tvId);
                String subsite_Id = tView.getText().toString();
                Log.d("SiteId-->",subsite_Id);
                String userId = dbase.getUserId(subsite_Id);
                Log.d("UserId-->",userId);
                String isSubsites = dbase.getIssubsites(userId);
                showProgressDialog();
                getAtlonaDevices(token,siteId,subsite_Id);

            }
        });

    }

    public ArrayList<Subsites> getSubsiteList(){
        ArrayList<Subsites> subsiteList = new ArrayList<Subsites>();
        subsiteList = dbase.getSubsites();
        if(!subsiteList.isEmpty()){
            for (int i=0; i<subsiteList.size(); i++){
                Subsites sitesGetset = subsiteList.get(i);
                mData.add(sitesGetset.getSubsiteName());
                mId.add(sitesGetset.getSubsiteId());
            }
        }
        return null;
    }

    public void getAtlonaDevices(final String token, final String siteId, final String subsiteId){
        String devUrl = "http://ec2-52-38-248-216.us-west-2.compute.amazonaws.com:1337/MobileSync/getAtlonaDevices";

        StringRequest devRequest = new StringRequest(Request.Method.POST, devUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                Log.d("Atlona Devices-->", str);
                try{
                    JSONArray jsonArray = new JSONArray(str);
                    for(int i=0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Gson gson = new Gson();
                            AtlonaDevices atlona = gson.fromJson(jsonObject.toString(),AtlonaDevices.class);
                            dbase.addAtlonaDevices(atlona);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                hideProgressDialog();
                getDevicesList(token,siteId,subsiteId,"1");
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
                params.put("siteid", siteId);
                params.put("subsiteid", subsiteId);
                return params;
            }
        };
        RequestQueue reQueue = Volley.newRequestQueue(mContext);
        reQueue.add(devRequest);
    }

    public void getDevicesList(final String token,final String siteid, final String subsiteId, final String isSubsites){
        String devUrl = "http://ec2-52-38-248-216.us-west-2.compute.amazonaws.com:1337/MobileSync/getDevices";
        StringRequest devRequest = new StringRequest(Request.Method.POST, devUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String str) {
                Log.d("DevicesList", str);
                try{
                    JSONArray jsonArray = new JSONArray(str);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Gson gson = new Gson();
                        Devices devices = gson.fromJson(jsonObject.toString(),Devices.class);
                        dbase.addDevices(devices.getDevConfigId(),devices.getUserId(),devices.getDeviceId(),
                                devices.getInoutType(),devices.getDevName(),devices.getDevBrand(),devices.getDevModel(),
                                devices.getDevProtocol(),devices.getControlDevice(),devices.getInpDeviceId(),
                                devices.getDeviceName(),devices.getDeviceNumber(),devices.getCurrowId(),devices.getCurInput(),devices.getPortNum(),devices.getIsActive(),
                                devices.getCreatedOn(),devices.getModifiedOn(),devices.getSiteId(),devices.getIsDiv());
                        Log.d("Device-->", devices.getDevName());
                     //   Log.d("Inoutttttttttttt-->", devices.getInoutType());
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                hideProgressDialog();
                Intent intent = new Intent(GetSubsites.this, HomeScreen.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("Site_ID",siteid);
                startActivity(intent);
                GetSubsites.this.finish();
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
                params.put("siteid", subsiteId);
                params.put("issubsite", isSubsites);
                return params;
            }
        };
        RequestQueue reQueue = Volley.newRequestQueue(mContext);
        reQueue.add(devRequest);
    }


    private void showProgressDialog() {
        if (!mProgress.isShowing())
            mProgress.show();
    }

    private void hideProgressDialog() {
        if (mProgress.isShowing())
            mProgress.hide();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(GetSubsites.this,GetSites.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        GetSubsites.this.finish();
    }
}
