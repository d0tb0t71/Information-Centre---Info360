package com.example.informationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class AddYourNumber extends AppCompatActivity {

    String[] category;
    Spinner spinner;
    Button add_btn;
    EditText add_mobile,add_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_number);

        category = getResources().getStringArray(R.array.category);

        spinner = findViewById(R.id.spinnerID);
        add_btn = findViewById(R.id.add_btn);
        add_mobile=findViewById(R.id.add_mobile);
        add_name= findViewById(R.id.add_name);





        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.sample_view_spinner,R.id.txt_view_sampleID,category);
        spinner.setAdapter(adapter);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Number Added", Toast.LENGTH_SHORT).show();

                String Category = spinner.getSelectedItem().toString();
                String Name = add_name.getText().toString();
                String Mobile = add_mobile.getText().toString();

                if(!Mobile.equals("")||!Name.equals("")){

                    HashMap<Object, String> hashMap = new HashMap<>();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = user.getUid();

                    hashMap.put("addedBy", userID);
                    hashMap.put("name", Name);
                    hashMap.put("mobile", Mobile);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference(Category);

                    reference.push().setValue(hashMap);

                    Toast.makeText(getApplicationContext(), "Number Added", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),EmergencyServices.class));
                    finish();
                }

            }
        });




    }
}