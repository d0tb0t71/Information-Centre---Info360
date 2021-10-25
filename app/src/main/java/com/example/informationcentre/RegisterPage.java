package com.example.informationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterPage extends AppCompatActivity {

        Button register_btn;
        TextView register_to_login;
        EditText regi_name,regi_email,regi_phone,regi_password,regi_confirm_password,regi_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        getSupportActionBar().hide();

        register_to_login=findViewById(R.id.register_to_login);

        register_btn=findViewById(R.id.register_btn);

        regi_name=findViewById(R.id.regi_name);
        regi_email =findViewById(R.id.regi_email);
        regi_phone =findViewById(R.id.regi_phone);
        regi_password =findViewById(R.id.regi_password);
        regi_confirm_password =findViewById(R.id.regi_confirm_password);
        regi_address =findViewById(R.id.regi_address);


        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name=regi_name.getText().toString();
                String email=regi_email.getText().toString();
                String phone=regi_phone.getText().toString();
                String address=regi_address.getText().toString();
                String pass= regi_password.getText().toString();
                String con_pass=regi_confirm_password.getText().toString();

                FirebaseAuth mAuth= FirebaseAuth.getInstance();

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {


                        if (task.isSuccessful()) {

                            FirebaseUser user=mAuth.getCurrentUser();

                            String uid= user.getUid();


                            HashMap<Object ,String> hashMap = new HashMap<>();

                            hashMap.put("email",email);
                            hashMap.put("uid",uid);
                            hashMap.put("name",name);
                            hashMap.put("phone",phone);
                            hashMap.put("address",address);

                            FirebaseDatabase database=FirebaseDatabase.getInstance();
                            DatabaseReference reference=database.getReference("Users");
                            reference.child(uid).setValue(hashMap);


                            Toast.makeText(getApplicationContext(), "User Registered Successfully", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(getApplicationContext(), HomePage.class ));
                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Registration Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
                Toast.makeText(getApplicationContext(), "Register Clicked", Toast.LENGTH_SHORT).show();
            }
        });


        register_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(RegisterPage.this,LoginPage.class));

            }
        });





    }
}