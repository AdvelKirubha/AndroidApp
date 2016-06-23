package com.homemonitoring;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.homemonitoring.HomeScreen;
import com.utils.Devices;
import com.utils.GetPrefsValue;
import com.utils.VerticalSeekBar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 *
 */



//  public class ResultFragment extends Fragment implements View.OnClickListener{

    public class ResultFragment extends Fragment {

        TextView tv;
        View view;
        //public SeekBar volume;
    VerticalSeekBar vSeekbar;

    GetPrefsValue getPrefs;

    Context mContext;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_result, container, false);
          //  volume =  (SeekBar) view.findViewById(R.id.seekBarFrag);
                    vSeekbar =  (VerticalSeekBar) view.findViewById(R.id.seekBarFrag);
                    mContext = getContext();
                    getPrefs = new GetPrefsValue(mContext);


            //  tv = (TextView) view.findViewById(R.id.textView);


//            Intent intentt = getIntent();
//            Bundle extrass =intentt.getExtras();
//
//            if(extrass!=null) {
//                String screenString = extrass.getString("STRING_I_NEED");


            Bundle args = getArguments();
            String switchCmndID = args.getString("STRING_I_NEED");
            Log.e("myString----->",""+switchCmndID);


                getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    vSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                            tv.setText(String.valueOf(seekBar.getProgress()));

                         String volumev=String.valueOf(seekBar.getProgress());



                            Log.e("CurrentVolume----->",""+volumev);
                       //     Toast.makeText(getContext(),"Volumes-->"+volumev,Toast.LENGTH_LONG).show();


                            getPrefs.setPrefs("Volumevv",  volumev);        //String.valueOf(seekBar.getProgress()));

                        }


                        //Toast.makeText(getContext(),"Volumess-->"+volumev,Toast.LENGTH_LONG).show();


                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                            String curVol = getPrefs.getPrefsValue("Volume");
                            Toast.makeText(getContext(),"set Volume from pref-->"+curVol,Toast.LENGTH_LONG).show();

                            vSeekbar.setProgressAndThumb( Integer.parseInt(curVol) );

                            int volume = Integer.parseInt(curVol)-80;
                            if(volume > 6){
                                volume = 6;
                            }
                            Log.d("CurrentVolumee-->",""+volume);
                            ;
                          //  new GetsessionIdOne("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",String.valueOf(volume)).execute();



                        //   Toast.makeText(getContext(),"Volumess-->"+volume,Toast.LENGTH_LONG).show();
//
//
//                            String curVol = getPrefs.getPrefsValue("Volumevv");
//                            Toast.makeText(getContext(),"Volume-->"+curVol,Toast.LENGTH_LONG).show();
//                            vSeekbar.setProgressAndThumb( Integer.parseInt(curVol) );
//
//                            int volume = Integer.parseInt(curVol)-80;
//                            if(volume > 6){
//                                volume = 6;
//                            }
//                            Log.d("CurrentVolume-->",""+volume);
////                            new HomeScreen.GetsessionIdOne("http://"+ipAddress+"/ajlogin.html?value=login&usn=root&pwd=Atlona",String.valueOf(volume)).execute();



                        }

                    });
                }
            });



            return view;
        }

        public int getVolume(){
            return vSeekbar.getProgress();
        }

        public void setVolume(int progress){
            vSeekbar.setProgress(progress);
        }

    }