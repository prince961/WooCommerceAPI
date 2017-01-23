package com.example.princ.woocommerceapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by princ on 24-11-2016.
 */

public class CategoriesFragmenent2 extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.categories_fragment_layout,container,false);
        //FragmentManager fragmentManager = getFragmentManager();

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);

        ImageView noodleImage = (ImageView) myView.findViewById(R.id.noodleImage);
        //Button button = new Button()
        noodleImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new NoodleFragment()).addToBackStack(null).commit();

            }
        });

        ImageView starterImage = (ImageView) myView.findViewById(R.id.imageView3);
        //Button button = new Button()
        starterImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new StarterFragment()).addToBackStack(null).commit();

            }
        });

        ImageView nonVegMain = (ImageView) myView.findViewById(R.id.imageView4);
        //Button button = new Button()
        nonVegMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new NonVegMainCourseFragment()).addToBackStack(null).commit();

            }
        });
        ImageView VegMain = (ImageView) myView.findViewById(R.id.imageView2);
        //Button button = new Button()
        VegMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new VegMainCourseFragment()).addToBackStack(null).commit();

            }
        });

        ImageView Breads = (ImageView) myView.findViewById(R.id.Breads);
        //Button button = new Button()
        Breads.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main, new BreadsFragment()).addToBackStack(null).commit();

            }
        });
        return myView;
    }
}
