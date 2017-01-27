package com.example.princ.woocommerceapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by princ on 25-01-2017.
 */

public class MyAccountFragment extends Fragment {


    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.call_fragment_layout,container,false);


        return myView;
    }
}
