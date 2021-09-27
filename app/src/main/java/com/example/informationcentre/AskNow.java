package com.example.informationcentre;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class AskNow extends AppCompatActivity {

    EditText name_help,mobile_help,txt_help;
    Button cancel_help,add_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_now);

        name_help = findViewById(R.id.name_help);
        mobile_help = findViewById(R.id.mobile_help);
        txt_help = findViewById(R.id.txt_help);


        cancel_help = findViewById(R.id.cancel_help);
        add_help = findViewById(R.id.add_help);


        cancel_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AskForHelp.class));
                finish();

            }
        });

        add_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name = name_help.getText().toString();
                String Mobile = mobile_help.getText().toString();
                String Mssg = txt_help.getText().toString();


                if(!Name.equals("")||!Mobile.equals("")||!Mssg.equals("")){

                    HashMap<Object, String> hashMap = new HashMap<>();

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String userID = user.getUid();

                    hashMap.put("addedBy", userID);
                    hashMap.put("name", Name);
                    hashMap.put("mobile", Mobile);
                    hashMap.put("mssg", Mssg);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("Need Help");

                    reference.push().setValue(hashMap);


                    Toast.makeText(getApplicationContext(), "Help Asked", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(),HomePage.class));
                    finish();

                }



            }
        });




    }
}