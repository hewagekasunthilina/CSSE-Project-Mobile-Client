package com.example.mobile_client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Site_Manager_Dashboard extends AppCompatActivity {

    private CardView create, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site__manager__dashboard);

        view = (CardView) findViewById(R.id.ViewPo);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Site_Manager_Dashboard.this,View_PO.class);
                startActivity(intent);
            }
        });
    }
}