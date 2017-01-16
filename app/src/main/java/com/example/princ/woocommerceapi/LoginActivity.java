package com.example.princ.woocommerceapi;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button bLogin;
    EditText etUsername , etPassword;
    TextView tvRegister;
    //UserLocalStore userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etphone);
        etPassword = (EditText) findViewById(R.id.etPasswordLogin);

    }


    public void RegisterC(View view) {
        Intent intent= new Intent(this,Register.class);
        startActivity(intent);
    }
}
