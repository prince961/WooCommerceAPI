package com.example.princ.woocommerceapi;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by princ on 25-11-2016.
 */

public class BreadsFragment extends Fragment {

    Controller controller = null;
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_noodle,container,false);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);


        ListView listView = (ListView) myView.findViewById(R.id.LvNoodle);
        ImageView categoryImage = (ImageView) myView.findViewById(R.id.IvNoodleCat);
        Drawable catImage = getResources().getDrawable(R.drawable.paneer);
        categoryImage.setImageDrawable(catImage);
        controller = (Controller) getActivity().getApplicationContext();

        boolean productInCart = controller.checkbreadsId(501);
        if (productInCart==false) {

            ArrayList<ModelProducts> noodleProducts = new ArrayList<>();
            ModelProducts Veg65 = new ModelProducts("Tandoori Roti", " ", 10, 0,501);
            ModelProducts PaneerMbc = new ModelProducts("Butter Roti", "Veg Noodle with a twist of Schezwan", 20, 0,502);
            ModelProducts vegTripleNoodle = new ModelProducts("Pudina Parantha", "Veg. Triple Noodle", 35, 0,503);
            ModelProducts vegMunchurianNoodle = new ModelProducts("Laccha Parantha", "Veg Munchurian Noodle", 30, 0,504);
            ModelProducts PaneerNoodle = new ModelProducts("Naan", "Enjoy your Noodle with Paneer", 120, 0,505);
            ModelProducts EggNoodle = new ModelProducts("Paneer Tikka", "Noodle With eggs", 20, 0,506);
            ModelProducts chickenHakkaNoodle = new ModelProducts("Butter naan", "Delicious hakka noodle with chicken", 190, 0,507);
            ModelProducts chickenSchezwanNoodle = new ModelProducts("Garlic Naan", "Chicken Noodle with schezwan sauce", 35, 0,508);
            ModelProducts chickenTripleNoodle = new ModelProducts("Kulcha", "Chicken Triple Noodle", 20, 0,509);
            ModelProducts chickenManchurianNoodle = new ModelProducts("Paneer Parantha", "Chicken noodle with munchurian gravy", 40, 0,510);
            noodleProducts.add(0, Veg65);
            noodleProducts.add(1, PaneerMbc);
            noodleProducts.add(2, vegTripleNoodle);
            noodleProducts.add(3, vegMunchurianNoodle);
            noodleProducts.add(4, PaneerNoodle);
            noodleProducts.add(5, EggNoodle);
            noodleProducts.add(6, chickenHakkaNoodle);
            noodleProducts.add(7, chickenManchurianNoodle);
            noodleProducts.add(8, chickenSchezwanNoodle);
            noodleProducts.add(9, chickenTripleNoodle);

            controller.setBreads(noodleProducts);

            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
            listView.setAdapter(listAdapterImagLess);

            return myView;
        }else {
            ArrayList<ModelProducts> categoryList = controller.getBreads();
            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, categoryList, controller);
            listView.setAdapter(listAdapterImagLess);
            return myView;
        }
    }



  //n ftxg = fragmentManager.beginTransaction().replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();







}
