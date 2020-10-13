package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonStreamParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Ref;
import java.util.ArrayList;

public class View_PO extends AppCompatActivity {

    ListView listview;
    PoAdapter adapter;
    public static ArrayList<Po> PoArrayList = new ArrayList<>();
    String url = "http://mail.dimodigital.lk/Po_retrieve.php";
    Po po;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__p_o);

        listview = (ListView)findViewById(R.id.PoList);
        adapter = new PoAdapter(this,PoArrayList);
        listview.setAdapter(adapter);

        retrieveData();
    }

    private void retrieveData() {
        StringRequest request = new StringRequest(Request.Method.POST,url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                PoArrayList.clear();
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    if (success.equals("1")){
                        for (int i = 0; i < jsonArray.length(); i++){
                            JSONObject object = jsonArray.getJSONObject(i);

                            Integer OrderID = object.getInt("OrderID");
                            String RefNo = object.getString("RefNo");
                            String Material = object.getString("Material");
                            String Description = object.getString("Description");
                            String Supplier = object.getString("Supplier");
                            Double Price = object.getDouble("Price");
                            Double Quantity = object.getDouble("Quantity");
                            String Site = object.getString("Site");
                            String DelDate = object.getString("DelDate");

                            po = new Po(OrderID,RefNo,Material,Description,Supplier,Price,Quantity,Site,DelDate);

                            PoArrayList.add(po);
                            adapter.notifyDataSetChanged();

                        }
                    }

                } catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(View_PO.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

}
