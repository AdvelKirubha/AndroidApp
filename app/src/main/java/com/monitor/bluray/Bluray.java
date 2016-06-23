package com.monitor.bluray;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.homemonitoring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Bluray extends Fragment {


    public Bluray() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_bluray, container, false);

        return mView;
    }

}
