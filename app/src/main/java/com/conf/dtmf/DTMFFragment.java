package com.conf.dtmf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homemonitoring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DTMFFragment extends Fragment {


    public DTMFFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_dtmf, container, false);

        return mView;
    }

}
