package com.example.princ.woocommerceapi;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by princ on 25-11-2016.
 */

public class NoodleFragment extends Fragment {

    Controller controller = null;
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_noodle, container, false);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);


        ListView listViewNoodle = (ListView) myView.findViewById(R.id.LvNoodle);
        controller = (Controller) getActivity().getApplicationContext();

        ArrayList<ModelProducts> noodleProducts = controller.getCategoryProducts("Starters");
        ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
        listViewNoodle.setAdapter(listAdapterImagLess);
        return myView;


    }
}



  //n ftxg = fragmentManager.beginTransaction().replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();








