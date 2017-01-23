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

public class VegMainCourseFragment extends Fragment {

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

        boolean productInCart = controller.checkVegMainCourseId(301);
        if (productInCart==false) {

            ArrayList<ModelProducts> noodleProducts = new ArrayList<>();
            ModelProducts Veg65 = new ModelProducts("Veg 65", " ", 180, 0,301);
            ModelProducts PaneerMbc = new ModelProducts("Paneer Mushroom Babycorn Crispy", "Veg Noodle with a twist of Schezwan", 120, 0,302);
            ModelProducts vegTripleNoodle = new ModelProducts("Mushroom Garlic Chilli", "Veg. Triple Noodle", 130, 0,303);
            ModelProducts vegMunchurianNoodle = new ModelProducts("Mushroom Soyabean", "Veg Munchurian Noodle", 180, 0,304);
            ModelProducts PaneerNoodle = new ModelProducts("Veg Munchurian", "Enjoy your Noodle with Paneer", 120, 0,305);
            ModelProducts EggNoodle = new ModelProducts("Paneer Tikka", "Noodle With eggs", 180, 0,306);
            ModelProducts chickenHakkaNoodle = new ModelProducts("Paneer Butterr Masala", "Delicious hakka noodle with chicken", 190, 0, 307);
            ModelProducts chickenSchezwanNoodle = new ModelProducts("Paneer Angare", "Chicken Noodle with schezwan sauce", 150, 0,308);
            ModelProducts chickenTripleNoodle = new ModelProducts("Paneer Kadai", "Chicken Triple Noodle", 110, 0,309);
            ModelProducts chickenManchurianNoodle = new ModelProducts("Shahi Paneer", "Chicken noodle with munchurian gravy", 140, 0,310);
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

            controller.setVegMainCourse(noodleProducts);

            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
            listView.setAdapter(listAdapterImagLess);

            return myView;
        }else {
            ArrayList<ModelProducts> categoryList = controller.getVegMainCourse();
            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, categoryList, controller);
            listView.setAdapter(listAdapterImagLess);
            return myView;
        }
    }



  //n ftxg = fragmentManager.beginTransaction().replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();







}
