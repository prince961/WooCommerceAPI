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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by princ on 09-12-2016.
 */

public class CartFragment extends Fragment {

    Controller controller ;
    private View myView;
    //ArrayList<ModelProducts> cartProductList = aController.getCart().getCartProducts();
    ListView cListView;
    TextView totalPrice ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.cart_layout2,container,false);

        controller = (Controller) getActivity().getApplicationContext();
        cListView  = (ListView) myView.findViewById(R.id.cartLv);
        totalPrice = (TextView) myView.findViewById(R.id.totalAmount);
        ArrayList<ModelProducts> cartProductList = controller.getCart().getCartProducts();
        final CartListAdapter cartListAdapter = new CartListAdapter(getActivity().getBaseContext(),R.layout.cart_item,cartProductList,controller,totalPrice);
        cListView.setAdapter(cartListAdapter);
        inflateTotalAmount(cartProductList);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.setVisibility(View.INVISIBLE);



        return myView;
    }

    public void onBackPressed()
    {
        FragmentManager fm = getFragmentManager();
        fm.popBackStack();
    }


    private void inflateTotalAmount(ArrayList<ModelProducts> productList) {
        //ArrayList<Double> priceList = new ArrayList<Double>();
        Double totalAmount = 0.0;
        int numberProducts = productList.size();
        for (int i = 0; i < numberProducts; i++){
            int pQuantity =   productList.get(i).getProductQuantity();
            int pPrice = productList.get(i).getProductPrice();
            double pAmountI = pPrice*pQuantity;
            //priceList.add(pAmountI);
            totalAmount = totalAmount + pAmountI;
        }
        totalPrice.setText(Double.toString(totalAmount)+" Rs.");
    }

    public void proceedFromCart(View view) {
        Intent intent = new Intent(getActivity().getBaseContext(), AfterCart.class);
        startActivity(intent);
    }


}
