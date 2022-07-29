package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivitySignInBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {
ProgressDialog progressDialog;
ActivitySignInBinding binding;
private FirebaseAuth mAuth;
FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Diving you into your account");


        binding.SignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(binding.etMail.getText().toString(),binding.etPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(intent);

                        }

                        else{
                            Toast.makeText(SignInActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        binding.ToSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

if(mAuth.getCurrentUser()!=null){
    Intent intent = new Intent(SignInActivity.this,MainActivity.class);
    startActivity(intent);
}



    }
}