package com.monitor.computer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.homemonitoring.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Computer extends Fragment {


    public Computer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_computer, container, false);
        Toast.makeText(getActivity(), "Computer", Toast.LENGTH_LONG).show();
        return mView;
    }

}
