package com.example.princ.woocommerceapi;

/**
 * Created by princ on 16-01-2017.
 */
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class Register extends AppCompatActivity {

    EditText etname, etPhone, etpassword, etAddress, etEmail;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);

        etname = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etphone);
        etEmail = (EditText) findViewById(R.id.etemail);
        etAddress = (EditText) findViewById(R.id.etAddress);
        etpassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.bRegister);
    }


    String body = new String("{\"customer\": {\r\n\"email\": \"5john.doe@example.com\",\r\n  \"first_name\": \"John\",\r\n  \"last_name\": \"Doe\",\r\n  \"username\": \"5john.doe\",\r\n  \"password\":\"123456\",\r\n  \"billing_address\": {\r\n    \"first_name\": \"John\",\r\n    \"last_name\": \"Doe\",\r\n    \"company\": \"\",\r\n    \"address_1\": \"969 Market\",\r\n    \"address_2\": \"\",\r\n    \"city\": \"San Francisco\",\r\n    \"state\": \"CA\",\r\n    \"postcode\": \"94103\",\r\n    \"country\": \"US\",\r\n    \"email\": \"john.doe@example.com\",\r\n    \"phone\": \"(555) 555-5555\"\r\n  },\r\n  \"shipping_address\": {\r\n    \"first_name\": \"John\",\r\n    \"last_name\": \"Doe\",\r\n    \"company\": \"\",\r\n    \"address_1\": \"969 Market\",\r\n    \"address_2\": \"\",\r\n    \"city\": \"San Francisco\",\r\n    \"state\": \"CA\",\r\n    \"postcode\": \"94103\",\r\n    \"country\": \"US\"\r\n  }\r\n}}");





    public void RegisterD(View view) {

        new StoreUserDataAsyncTask().execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {

          /*  Map<String, String> dataToSend = new HashMap<>();
            dataToSend.put("name", user.name);
            dataToSend.put("age", user.age + "");
            dataToSend.put("username", user.username);
            dataToSend.put("password", user.password); */

            BufferedReader reader = null;



            try {
                URL url = new URL("https://www.jersershor.com/wc-api/v3/customers?consumer_key=ck_638caaf46271a320075ecee01e89581f91644b98&consumer_secret=cs_0f5fe1845a21396a459fc3961a8255d15a62970b");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                //int responseCode  = conn.getResponseCode();
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(body);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, you will have to implement the procedure below
                writer.flush();
                int responseCode  = conn.getResponseCode();


                if (responseCode >= 200 && responseCode < 400) {
                    // Create an InputStream in order to extract the response object
                   //InputStream is = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        //Read till there is something available
                        sb.append(line + "\n");     //Reading and saving line by line - not all at once
                    }
                    line = sb.toString();           //Saving complete data received in string, you can do it differently

                    //Just check to the values received in Logcat
                    Log.i("custom_check", "The values received in the store part are as follows:");
                    Log.i("custom_check", line);
                    Log.i("custom_check", Integer.toString(responseCode));
                }
                else {
                   //InputStream is = conn.getErrorStream();
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        //Read till there is something available
                        sb.append(line + "\n");     //Reading and saving line by line - not all at once
                    }
                    line = sb.toString();           //Saving complete data received in string, you can do it differently

                    //Just check to the values received in Logcat
                    Log.i("custom_check", "The values received in the store part are as follows:");
                    Log.i("custom_check", line);
                    Log.i("custom_check", Integer.toString(responseCode));

                }


                //Data Read Procedure - Basically reading the data comming line by line


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();     //Closing the
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }




        @Override
        protected void onPostExecute(Void aVoid) {
            //progressDialog.dismiss();
            //userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

}

