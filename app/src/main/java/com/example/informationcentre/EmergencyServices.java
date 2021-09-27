package com.example.informationcentre;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EmergencyServices extends AppCompatActivity {

    ImageView add_your_name,ambulance,hospital,pharmacy,police,delivery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_services);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Emergency Services");

        add_your_name=findViewById(R.id.add_your_name);
        ambulance=findViewById(R.id.ambulance);
        hospital=findViewById(R.id.hospital);
        pharmacy=findViewById(R.id.pharmachy);
        police=findViewById(R.id.police);
        delivery=findViewById(R.id.delivery);


        add_your_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AddYourNumber.class));
            }
        });

        ambulance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListRecycler.class);
                intent.putExtra("category","Ambulance");
                startActivity(intent);
            }
        });

        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListRecycler.class);
                intent.putExtra("category","Hospital");
                startActivity(intent);
            }
        });


        pharmacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListRecycler.class);
                intent.putExtra("category","Pharmacy");
                startActivity(intent);
            }
        });

        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListRecycler.class);
                intent.putExtra("category","Police");
                startActivity(intent);
            }
        });

        delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ListRecycler.class);
                intent.putExtra("category","Delivery Services");
                startActivity(intent);
            }
        });







    }
}