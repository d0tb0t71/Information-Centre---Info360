package com.example.informationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListRecycler extends AppCompatActivity {

    TextView passed_txt;
    RecyclerView recyclerView;

    AdapterContact adapterContact;
    ArrayList<ModelContact> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_recycler);

        String Category = getIntent().getStringExtra("category");

        getSupportActionBar().setTitle(Category);


        passed_txt = findViewById(R.id.passed_txt);




        recyclerView  = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(ListRecycler.this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();

        adapterContact = new AdapterContact(this,list);
        recyclerView.setAdapter(adapterContact);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference(Category);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ModelContact modelContact = dataSnapshot.getValue(ModelContact.class);

                    list.add(modelContact);
                }

                adapterContact.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        passed_txt.setText(Category);








    }
}