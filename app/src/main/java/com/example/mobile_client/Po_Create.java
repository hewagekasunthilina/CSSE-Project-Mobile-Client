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
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Po_Create extends AppCompatActivity {

    Spinner spinner1;
    ArrayList<String> materialList = new ArrayList<>();
    ArrayAdapter<String> materialAdapter;
    RequestQueue requestQueue;
    private Button po;
    private TextInputEditText RefNo,Material,Description, Price, Quantity, Site, DelDate, Status, CheckedBy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_po__create);

        RefNo = (TextInputEditText)findViewById(R.id.RefNo);
        Material = (TextInputEditText)findViewById(R.id.Material);
        Description = (TextInputEditText)findViewById(R.id.Description);
        spinner1 = (Spinner)findViewById(R.id.spinner);
        Price = (TextInputEditText)findViewById(R.id.Price);
        Quantity = (TextInputEditText)findViewById(R.id.Quantity);
        Site = (TextInputEditText)findViewById(R.id.Site);
        DelDate = (TextInputEditText)findViewById(R.id.DelDate);
        Status = (TextInputEditText)findViewById(R.id.Status);
        CheckedBy = (TextInputEditText)findViewById(R.id.CheckedBy);

        po = (Button)findViewById(R.id.addPo);
        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient().newBuilder()
                                .build();
                        MediaType mediaType = MediaType.parse("text/plain");
                        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                .addFormDataPart("RefNo", RefNo.getText().toString())
                                .addFormDataPart("Material", Material.getText().toString())
                                .addFormDataPart("Description", Description.getText().toString())
                                .addFormDataPart("Supplier", spinner1.getSelectedItem().toString())
                                .addFormDataPart("Price", Price.getText().toString())
                                .addFormDataPart("Quantity", Quantity.getText().toString())
                                .addFormDataPart("Site", Site.getText().toString())
                                .addFormDataPart("DelDate", DelDate.getText().toString())
                                .addFormDataPart("Status", Status.getText().toString())
                                .addFormDataPart("CheckedBy", CheckedBy.getText().toString())
                                .build();
                        okhttp3.Request request = new okhttp3.Request.Builder()
                                .url("http://mail.dimodigital.lk/create_po.php")
                                .method("POST", body)
                                .build();
                        try {
                            okhttp3.Response response = client.newCall(request).execute();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        openViewPo();
                    }

                    private void openViewPo() {
                        Intent intent = new Intent(Po_Create.this, View_PO.class);
                        startActivity(intent);
                    }
                });
            }
        });

        requestQueue = Volley.newRequestQueue(this);
        spinner1 = (Spinner)findViewById(R.id.spinner);
        String url = "http://mail.dimodigital.lk/supplier_list.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("supplier");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String materialName = jsonObject.optString("Supplier");
                        materialList.add(materialName);
                        materialAdapter = new ArrayAdapter<>(Po_Create.this, android.R.layout.simple_spinner_item, materialList);
                        materialAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner1.setAdapter(materialAdapter);
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