package com.example.informationcentre;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class LoginPage extends AppCompatActivity {

        Button login_btn;
        TextView login_to_register,forgot_password;
        EditText login_email,login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        getSupportActionBar().hide();

        login_to_register=findViewById(R.id.login_to_register);
        forgot_password=findViewById(R.id.forgot_password);

        login_btn=findViewById(R.id.login_btn);

        login_email=findViewById(R.id.login_email);
        login_password= findViewById(R.id.login_password);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=login_email.getText().toString();
                String pass=login_password.getText().toString();

                FirebaseAuth mAuth=FirebaseAuth.getInstance();

                mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),HomePage.class));

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Failed\n"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                Toast.makeText(getApplicationContext(), "Login Now", Toast.LENGTH_SHORT).show();
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Forgot Password", Toast.LENGTH_SHORT).show();
            }
        });

        login_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,RegisterPage.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            startActivity(new Intent(LoginPage.this,HomePage.class));
        }




    }
}