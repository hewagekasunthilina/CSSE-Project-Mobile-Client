package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Supplier_Register extends AppCompatActivity {

    //Create objects
    ArrayList<String> materialList = new ArrayList<>();
    ArrayAdapter<String> materialAdapter;
    RequestQueue requestQueue;

    //Create objects
    private TextInputEditText name, Email, ContactNo, Password, price;
    private Button register;
    private Spinner material;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__register);

        //Layout ID confirmation
        name = (TextInputEditText) findViewById(R.id.name);
        Email = (TextInputEditText) findViewById(R.id.Email);
        ContactNo = (TextInputEditText) findViewById(R.id.ContactNo);
        Password = (TextInputEditText) findViewById(R.id.Password);
        register = (Button) findViewById(R.id.btnRegister);
        material = (Spinner) findViewById(R.id.MaterialSpinner);
        price = (TextInputEditText) findViewById(R.id.Price);

        //Button click method
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validation start
                String NameInput = name.getText().toString().trim();
                String EmailInput = Email.getText().toString().trim();
                String ContactInput = ContactNo.getText().toString().trim();
                String MaterialInput = Password.getText().toString().trim();
                String PriceInput = price.getText().toString().trim();

                //Check the empty fields
                if (NameInput.isEmpty()) {
                    name.setError("Name Can't be Empty");
                } else if (EmailInput.isEmpty()) {
                    Email.setError("Email Can't be Empty");
                } else if (ContactInput.isEmpty()) {
                    ContactNo.setError("Contact Number Can't be Empty");
                } else if (MaterialInput.isEmpty()) {
                    Password.setError("Password Can't be Empty");
                } else if (PriceInput.isEmpty()) {
                    price.setError("Price Can't be Empty");
                } else {

                    //Asynchronise method run start
                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient client = new OkHttpClient().newBuilder()
                                    .build();
                            MediaType mediaType = MediaType.parse("text/plain");
                            RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                    .addFormDataPart("name", name.getText().toString())
                                    .addFormDataPart("Email", Email.getText().toString())
                                    .addFormDataPart("ContactNo", ContactNo.getText().toString())
                                    .addFormDataPart("password", Password.getText().toString())
                                    .addFormDataPart("Material", material.getSelectedItem().toString())
                                    .addFormDataPart("Price", price.getText().toString())
                                    .build();
                            okhttp3.Request request = new okhttp3.Request.Builder()
                                    .url("http://mail.dimodigital.lk/supregister.php")
                                    .method("POST", body)
                                    .build();
                            try {
                                okhttp3.Response response = client.newCall(request).execute();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            opensupsigninpage();
                        }

                        //Intent call
                        private void opensupsigninpage() {
                            Intent intent = new Intent(Supplier_Register.this, Supplier_Dashboard.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        //Request methods for dropdown values
        requestQueue = Volley.newRequestQueue(this);
        material = (Spinner) findViewById(R.id.MaterialSpinner);
        String url = "http://mail.dimodigital.lk/material.php";

        //Retrieve value to JSON object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("material");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String materialName = jsonObject.optString("MatName");
                        materialList.add(materialName);
                        materialAdapter = new ArrayAdapter<>(Supplier_Register.this, android.R.layout.simple_spinner_item, materialList);
                        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        material.setAdapter(materialAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}
