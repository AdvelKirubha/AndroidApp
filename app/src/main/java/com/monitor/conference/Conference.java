package com.monitor.conference;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.conf.camera.Camera;
import com.conf.contacts.Contacts;
import com.conf.dtmf.DTMFFragment;
import com.conf.monitor.MonitorControl;
import com.conf.videocall.Videocall;
import com.homemonitoring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Conference extends Fragment implements View.OnClickListener {

    LinearLayout mVideocall, mCamera, mDtmf, mContacts, mMonitor;


    public Conference() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_conference, container, false);

        mVideocall = (LinearLayout) mView.findViewById(R.id.vidCalLinear);
        mCamera = (LinearLayout) mView.findViewById(R.id.cameraLinear);
        mDtmf = (LinearLayout) mView.findViewById(R.id.dtmfLinear);
        mContacts = (LinearLayout) mView.findViewById(R.id.contactsLinear);
        mMonitor = (LinearLayout) mView.findViewById(R.id.monitorLinear);

        mVideocall.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mDtmf.setOnClickListener(this);
        mContacts.setOnClickListener(this);
        mMonitor.setOnClickListener(this);

        FragmentManager vidFragment = getFragmentManager();
        FragmentTransaction vTransaction = vidFragment.beginTransaction();
        Videocall videoCall = new Videocall();
        vTransaction.replace(R.id.confContainer, videoCall);
        vTransaction.commit();

        return mView;

    }

    @Override
    public void onClick(View v) {

        if(v == mVideocall){
            FragmentManager vidFragment = getFragmentManager();
            FragmentTransaction vTransaction = vidFragment.beginTransaction();
            Videocall videoCall = new Videocall();
            vTransaction.replace(R.id.confContainer,videoCall);
            vTransaction.commit();
        }else if(v == mCamera){
            FragmentManager camFragment = getFragmentManager();
            FragmentTransaction cTransaction = camFragment.beginTransaction();
            Camera camera = new Camera();
            cTransaction.replace(R.id.confContainer,camera);
            cTransaction.commit();
        }else if(v == mDtmf){
            FragmentManager dtmfFragment = getFragmentManager();
            FragmentTransaction dTransaction = dtmfFragment.beginTransaction();
            DTMFFragment dtmfFrag = new DTMFFragment();
            dTransaction.replace(R.id.confContainer,dtmfFrag);
            dTransaction.commit();
        }else if(v == mContacts){
            FragmentManager conFragment = getFragmentManager();
            FragmentTransaction conTransaction = conFragment.beginTransaction();
            Contacts contacts = new Contacts();
            conTransaction.replace(R.id.confContainer,contacts);
            conTransaction.commit();
        }else if(v == mMonitor){
            FragmentManager monFragment = getFragmentManager();
            FragmentTransaction mTransaction = monFragment.beginTransaction();
            MonitorControl monControl = new MonitorControl();
            mTransaction.replace(R.id.confContainer,monControl);
            mTransaction.commit();
        }
    }
}
