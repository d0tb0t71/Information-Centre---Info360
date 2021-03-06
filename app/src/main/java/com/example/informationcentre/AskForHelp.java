package com.example.informationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AskForHelp extends AppCompatActivity {

    RecyclerView recyclerView;
    Button ask_btn;

    AdapterHelp adapterHelp;
    ArrayList<ModelHelp> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_help);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Ask For Help");




        recyclerView = findViewById(R.id.recyclerView);
        ask_btn = findViewById(R.id.ask_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(AskForHelp.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        adapterHelp = new AdapterHelp(this,list);
        recyclerView.setAdapter(adapterHelp);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Need Help");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelHelp modelHelp = dataSnapshot.getValue(ModelHelp.class);

                    list.add(modelHelp);
                }
                adapterHelp.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        ask_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AskNow.class));

            }
        });








    }
}