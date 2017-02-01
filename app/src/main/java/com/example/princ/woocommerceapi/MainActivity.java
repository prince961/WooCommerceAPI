package com.example.princ.woocommerceapi;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Controller controller = null;
    private boolean allProductsAdded ;

    UserLocalStore userLocalStore;
    FragmentManager fragmentManager = getFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLocalStore = new UserLocalStore(this);
    }

    private class DownloadProducts extends AsyncTask<String, Void,String>{
        String data = null;
        InputStream iStream = null;
        @Override
        protected String doInBackground(String... url1) {
            //we dont do anything with the url1
            try {
                URL url = new URL("https://www.jersershor.com/wc-api/v3/products?consumer_key=ck_638caaf46271a320075ecee01e89581f91644b98&consumer_secret=cs_0f5fe1845a21396a459fc3961a8255d15a62970b");
                //URL url = new URL(strUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                iStream = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
                StringBuffer sb = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                data = sb.toString();
                //Log.i("String_InputString", data);
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (iStream != null){
                    iStream.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jObject = null;
            try {
                jObject = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JsonParserProducts jsonParserProducts = new JsonParserProducts();
            ArrayList<ModelProducts> AllProducts = jsonParserProducts.parse(jObject);
            //ModelProducts modelProducts = AllProducts.get(0);
            //modelProducts.getCategories()
            controller.addAllProducts(AllProducts);
            controller.setAllProductsAdded(true);
            Log.i("main_act_allproducts",Boolean.toString(controller.isAllProductsAdded()));
            //DownloadUserDetails downloadUserDetails = new DownloadUserDetails();
            //downloadUserDetails.execute();

        }
    }

    private class DownloadUserDetails extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {

            InputStream inputStream2 = null;
            String data2 = null;
            String userEmail = userLocalStore.getUserEmail();
            String userUrl = "https://www.jersershor.com/wc-api/v3/customers/email/"+userEmail+"?consumer_key=ck_638caaf46271a320075ecee01e89581f91644b98&consumer_secret=cs_0f5fe1845a21396a459fc3961a8255d15a62970b";
            try {
                URL url = new URL(userUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                inputStream2 = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream2));
                StringBuffer sb2 = new StringBuffer();

                String line = "";
                while ((line = br.readLine()) != null) {
                    sb2.append(line);
                }
                data2 = sb2.toString();
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if ( inputStream2 != null){
                        inputStream2.close();}
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                JSONObject jsonObject = new JSONObject(data2);
                JSONObject jsonObjectUser = jsonObject.getJSONObject("customer");
                String UserName = jsonObjectUser.getString("first_name");
                int UserId = jsonObjectUser.getInt("id");
                JSONObject shippingAddressJobject = jsonObjectUser.getJSONObject("billing_address");
                String UserPhone = shippingAddressJobject.getString("phone");
                String UserAddress = shippingAddressJobject.getString("address_1");

                userLocalStore.storeUserData(UserName,UserPhone,UserAddress,UserId);
                Log.i("userdatastoredName",userLocalStore.getUserName());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        }
    }


/*
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkUserLocalStore()){
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            ImageView cartLogo = (ImageView) toolbar.findViewById(R.id.cartOnToolbar);
            fragmentManager.beginTransaction().replace(R.id.content_Frame, new CategoriesFragmenent2()).commit();
            cartLogo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_Frame, new CartFragment()).addToBackStack(null).commit();
                }
            });

            controller = (Controller) getApplicationContext();

            if (!controller.isAllProductsAdded()){
                DownloadProducts downloadProducts = new DownloadProducts();
                downloadProducts.execute();}

/*
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    //      .setAction("Action", null).show();
                    fragmentManager.beginTransaction().replace(R.id.content_Frame, new CartFragment()).addToBackStack(null).commit();

                }
            });
*/
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);



        }else {
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
            //Toast.makeText(getApplication(), Boolean.toString(userLocalStore.getUserLoggedIn()), Toast.LENGTH_LONG).show();
        }


    }

    private boolean checkUserLocalStore (){
        return userLocalStore.getUserLoggedIn();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            fragmentManager.beginTransaction().replace(R.id.content_Frame, new CategoriesFragmenent2()).addToBackStack(null).commit();
        } else if (id == R.id.nav_cart) {
            fragmentManager.beginTransaction().replace(R.id.content_Frame, new CartFragment()).addToBackStack(null).commit();

        } else if (id == R.id.contact_us) {

        } else if (id == R.id.nav_call) {
            fragmentManager.beginTransaction().replace(R.id.content_Frame, new CallFragment()).addToBackStack(null).commit();

        } else if (id == R.id.nav_User) {


        } else if (id == R.id.nav_Logout) {
            userLocalStore.SetUserLoggedIn(false);
            userLocalStore.ClearUserData();
            Intent intent = new Intent(getBaseContext(),LoginActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
