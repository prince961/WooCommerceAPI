package com.example.princ.woocommerceapi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button bLogin;
    EditText etUsername , etPassword;
    TextView tvRegister;
    String body;
    UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etphone);
        etPassword = (EditText) findViewById(R.id.etPasswordLogin);
        userLocalStore = new UserLocalStore(getBaseContext());

    }


    public void RegisterC(View view) {
        Intent intent= new Intent(this,Register.class);
        startActivity(intent);
    }

    public void LoginC(View view) {


        String phone = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        body = new String("{\"username\":\""+ phone +"\",\"password\":\""+ password +"\"}");

        new StoreUserDataAsyncTask().execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void> {

        int responseCode;
        JSONArray jsonErrorArray;
        String errorMessage;
        String errorCodeString;

        @Override
        protected Void doInBackground(Void... params) {

            BufferedReader reader = null;


            try {
                URL url = new URL("https://www.jersershor.com/wp-json/jwt-auth/v1/token");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setRequestProperty("Content-Type", "application/json");
                //int responseCode  = conn.getResponseCode();
                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                writer.write(body);
                //Sending the data to the server - This much is enough to send data to server
                //But to read the response of the server, you will have to implement the procedure below
                writer.flush();
                Log.i("custom_check", body);
                responseCode  = conn.getResponseCode();
                Log.i("response_code", Integer.toString(responseCode));


                if (responseCode >= 200 && responseCode < 400) {
                    // Create an InputStream in order to extract the response object
                    //InputStream is = conn.getInputStream();
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    userLocalStore.SetUserLoggedIn(true);


                    String line;
                    while ((line = reader.readLine()) != null) {
                        //Read till there is something available
                        sb.append(line + "\n");     //Reading and saving line by line - not all at once
                    }
                    line = sb.toString();           //Saving complete data received in string, you can do it differently

                    //Just check to the values received in Logcat
                    Log.i("custom_check", "The values received in the store part are as follows:");
                    Log.i("custom_check", line);
                    Log.i("response_code", Integer.toString(responseCode));


                }
                else {
                    //InputStream is = conn.getErrorStream();
                    StringBuilder sb = new StringBuilder();
                    reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        //Read till there is something available
                        sb.append(line + "\n");     //Reading and saving line by line - not all at once
                    }
                    line = sb.toString();
                    //Saving complete data received in string, you can do it differently
                    JSONObject jsonObject = new JSONObject(line);
                    //jsonErrorArray = jsonObject.getJSONArray("errors");
                    //JSONObject jsonErrorObject = jsonErrorArray.getJSONObject(0);
                    errorMessage = jsonObject.getString("message");
                    errorCodeString = jsonObject.getString("code");

                    //Just check to the values received in Logcat
                    //Toast.makeText(Register.this, "there is some error", Toast.LENGTH_SHORT).show();



                    Log.i("custom_check", "The values received in the store part are as follows:");
                    Log.i("custom_check", line);
                    Log.i("custom_check", errorMessage);
                    Log.i("Response_Code", Integer.toString(responseCode));

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
            if (responseCode >= 200 && responseCode < 400) {
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
            }
            else {
                if (Objects.equals(errorCodeString, "[jwt_auth] invalid_username")){
                    Toast.makeText(getBaseContext(), "Number is not registered" , Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getBaseContext(), "Incorrect Password" , Toast.LENGTH_LONG).show();

                }
                //InputStream is = conn.getErrorStream();

            }
            //userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
}
