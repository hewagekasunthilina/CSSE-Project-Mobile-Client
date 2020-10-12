package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class Supplier_Register extends AppCompatActivity {

    private TextInputEditText name,Email,ContactNo,Password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier__register);

        name = (TextInputEditText)findViewById(R.id.name);
        Email = (TextInputEditText)findViewById(R.id.Email);
        ContactNo = (TextInputEditText)findViewById(R.id.ContactNo);
        Password = (TextInputEditText)findViewById(R.id.Password);
        register = (Button)findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                    private void opensupsigninpage() {
                        Intent intent = new Intent(Supplier_Register.this, Supplier_Login.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    private void Register(){
//        final String name = this.name.getText().toString().trim();
//        final String Email = this.Email.getText().toString().trim();
//        final String ContactNo = this.ContactNo.getText().toString().trim();
//        final String Password = this.Password.getText().toString().trim();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://mail.dimodigital.lk/supregister.php", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//            try{
//                JSONObject jsonObject = new JSONObject(response);
//                String success = jsonObject.getString("success");
//
//                if (success.equals("1")){
//                    Toast.makeText(Supplier_Register.this, "Register Successful !", Toast.LENGTH_SHORT).show();
//                }
//            } catch(JSONException e){
//                e.printStackTrace();
//                Toast.makeText(Supplier_Register.this, "Register Errorrrr !" + e.toString(), Toast.LENGTH_SHORT).show();
//            }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(Supplier_Register.this, "Register Error !" + error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name);
//                params.put("email", Email);
//                params.put("contact", ContactNo);
//                params.put("password", Password);
//                return params;
//            }
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
    }
}