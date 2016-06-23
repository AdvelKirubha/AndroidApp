package com.monitor.doccam;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.homemonitoring.R;
import com.utils.DatabaseHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class Doccam extends Fragment implements View.OnClickListener {

    DatabaseHelper db;
    Button zoomin, zoomout;
    SharedPreferences sPrefs;


    public Doccam() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_doccam, container, false);
        db = new DatabaseHelper(getActivity());
        sPrefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        zoomin = (Button) mView.findViewById(R.id.zoomin);
        zoomout = (Button) mView.findViewById(R.id.zoomout);
        zoomin.setOnClickListener(this);
        zoomout.setOnClickListener(this);

        Toast.makeText(getActivity(), "Doc Cam", Toast.LENGTH_LONG).show();
        return  mView;
    }

    @Override
    public void onClick(View v) {
        if(v == zoomin){
            String strzoomin = sPrefs.getString("ZOOMIN",null);
            Toast.makeText(getActivity(), "ZoomIn "+strzoomin, Toast.LENGTH_LONG).show();
        }else if(v == zoomout){
            String strzoomout = sPrefs.getString("ZOOMOUT",null);
            Toast.makeText(getActivity(), "Zoomout "+strzoomout, Toast.LENGTH_LONG).show();
        }

    }
}
