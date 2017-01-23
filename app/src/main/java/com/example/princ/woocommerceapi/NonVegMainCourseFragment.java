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

public class NonVegMainCourseFragment extends Fragment {

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
        Drawable catImage = getResources().getDrawable(R.drawable.nonvegmain);
        categoryImage.setImageDrawable(catImage);
        controller = (Controller) getActivity().getApplicationContext();

        boolean productInCart = controller.checkNonVegMainCourseId(401);
        if (productInCart==false) {

            ArrayList<ModelProducts> noodleProducts = new ArrayList<>();
            ModelProducts vegNoodle = new ModelProducts("Chicken Lawabdar", "Vegetarian Noodles", 80, 0, 401);
            ModelProducts vegShezwanNoodle = new ModelProducts("Chicken Kalimirch Masala", "Veg Noodle with a twist of Schezwan", 90, 0, 402);
            ModelProducts vegTripleNoodle = new ModelProducts("Chicken Peshawari", "Veg. Triple Noodle", 100, 0, 403);
            ModelProducts vegMunchurianNoodle = new ModelProducts("Chicken Kadai", "Veg Munchurian Noodle", 90, 0, 404);
            ModelProducts PaneerNoodle = new ModelProducts("Tawa Chicken", "Enjoy your Noodle with Paneer", 100, 0, 405);
            ModelProducts EggNoodle = new ModelProducts("Chilli Chicken", "Noodle With eggs", 80, 0, 406);
            ModelProducts chickenHakkaNoodle = new ModelProducts("Chicken Soya Kadai", "Delicious hakka noodle with chicken", 90, 0, 407);
            ModelProducts chickenSchezwanNoodle = new ModelProducts("Chicken Handi", "Chicken Noodle with schezwan sauce", 100, 0, 408);
            ModelProducts chickenTripleNoodle = new ModelProducts("Chicken Patiala", "Chicken Triple Noodle", 110, 0, 409);
            ModelProducts chickenManchurianNoodle = new ModelProducts("Butter Chicken", "Chicken noodle with munchurian gravy", 100, 0, 410);
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

            controller.setNonVegMainAl(noodleProducts);

            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, noodleProducts, controller);
            listView.setAdapter(listAdapterImagLess);

            return myView;
        }else {
            ArrayList<ModelProducts> categoryList = controller.getNonVegMainAl();
            ListAdapterImagLess listAdapterImagLess = new ListAdapterImagLess(getActivity().getBaseContext(), R.layout.list_item_imageless, categoryList, controller);
            listView.setAdapter(listAdapterImagLess);
            return myView;
        }
    }



  //n ftxg = fragmentManager.beginTransaction().replace( R.id.fragment, new MyFragment() ).addToBackStack( "tag" ).commit();







}
