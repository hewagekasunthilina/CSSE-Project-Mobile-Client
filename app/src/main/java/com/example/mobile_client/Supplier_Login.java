package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Supplier_Login extends AppCompatActivity {

    TextView supSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__login);

        supSignUp = (TextView)findViewById(R.id.sup_signup);
        supSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supplier_Login.this, Supplier_Register.class);
                startActivity(intent);
            }
        });
    }
}