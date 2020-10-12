package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Supplier_Login extends AppCompatActivity {

    private TextInputEditText name, password;
    private Button supplierLogin;
    TextView supSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__login);

        name = (TextInputEditText)findViewById(R.id.name);
        password = (TextInputEditText)findViewById(R.id.password);
        supplierLogin = (Button)findViewById(R.id.supplier_Login);

        supSignUp = (TextView)findViewById(R.id.sup_signup);
        supSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Supplier_Login.this, Supplier_Register.class);
                startActivity(intent);
            }
        });

        supplierLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SupplierLogin();
            }


        });
    }

    private void SupplierLogin() {
        StringRequest request = new StringRequest(Request.Method.POST, "http://mail.dimodigital.lk/supplier_login.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("1")){
                    startActivity(new Intent(getApplicationContext(),Supplier_Dashboard.class));
                }else {
                    Toast.makeText(getApplicationContext(),"Wrong Username or Password",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }
}