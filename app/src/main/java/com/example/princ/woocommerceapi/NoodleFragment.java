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
        myView = inflater.inflate(R.layout.activity_noodle,container,false);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);


        ListView listViewNoodle = (ListView) myView.findViewById(R.id.LvNoodle);
        controller = (Controller) getActivity().getApplicationContext();

        boolean productInCart = controller.checkid(101);
        if (productInCart == false){

        ArrayList<ModelProducts> noodleProducts = new ArrayList<>();
        ModelProducts vegNoodle = new ModelProducts("Veg. Noodle", "Vegetarian Noodles", 80, 0,101);
        ModelProducts vegShezwanNoodle = new ModelProducts("Veg Schezwan Noodle", "Veg Noodle with a twist of Schezwan", 90, 0,102);
        ModelProducts vegTripleNoodle = new ModelProducts("Veg. Triple Noodle", "Veg. Triple Noodle", 100, 0,103);
        ModelProducts vegMunchurianNoodle = new ModelProducts("Veg Munchurian Noodle", "Veg Munchurian Noodle", 90, 0,104);
        ModelProducts PaneerNoodle = new ModelProducts("Paneer Noodle", "Enjoy your Noodle with Paneer", 100, 0,105);
        ModelProducts EggNoodle = new ModelProducts("Egg Noodle", "Noodle With eggs", 80, 0,106);
        ModelProducts chickenHakkaNoodle = new ModelProducts("Chicken Hakka Noodle", "Delicious hakka noodle with chicken", 90, 0,107);
        ModelProducts chickenSchezwanNoodle = new ModelProducts("Chicken Schezwan Noodle", "Chicken Noodle with schezwan sauce", 100, 0,108);
        ModelProducts chickenTripleNoodle = new ModelProducts("Chicken Triple Noodle", "Chicken Triple Noodle", 110, 0,109);
        ModelProducts chickenManchurianNoodle = new ModelProducts("Chicken Munchurian Noodle", "Chicken noodle with munchurian gravy", 100, 0,110);
        noodleProducts.add(0, vegNoodle);
        noodleProducts.add(1, vegShezwanNoodle);
        noodleProducts.add(2, vegTripleNoodle);
        noodleProducts.add(3, vegMunchurianNoodle);
        noodleProducts.add(4, PaneerNoodle);
        noodleProducts.add(5, EggNoodle);
        noodleProducts.add(6, chickenHakkaNoodle);
        noodleProducts.add(7, chickenManchurianNoodle);
        noodleProducts.add(8, chickenSchezwanNoodle);
        noodleProducts.add(9, chickenTripleNoodle);


        controller.addNoodlePs(noodleProducts);

        ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
        listViewNoodle.setAdapter(listAdapterImagLess);
            return myView;}

        else {
            ArrayList<ModelProducts> noodleProducts = controller.getNoodleProducts();
            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
            listViewNoodle.setAdapter(listAdapterImagLess);
            return myView;}
        }



    }



  //n ftxg = fragmentManager.beginTransaction().replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();








