package com.example.whatsappclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsappclone.Adapters.MessageAdap;
import com.example.whatsappclone.Models.MessageModels;
import com.example.whatsappclone.databinding.ActivityInsideChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class InsideChatActivity extends AppCompatActivity {
ActivityInsideChatBinding binding;
FirebaseDatabase database;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsideChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String senderId = auth.getUid();
        String recieverId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilepic = getIntent().getStringExtra("profilepic");

        binding.Usernameinsidechats.setText(userName);
        Picasso.get().load(profilepic).placeholder(R.drawable.facebook).into(binding.profileImage);
        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InsideChatActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessageModels> messageModels  = new ArrayList<>();

        final MessageAdap messageAdap = new MessageAdap(messageModels , this);
        binding.insideChatsRecycler.setAdapter(messageAdap);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.insideChatsRecycler.setLayoutManager(layoutManager);
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
final String senderRoom = senderId + recieverId;
final String recieverRoom = recieverId + senderId;
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String message  = binding.Message.getText().toString();
                    final MessageModels model = new MessageModels(senderId,message);
                    model.setTimestamp(new Date().getTime());
                    binding.Message.setText("");

                    database.getReference().child("chats").child(senderRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });

                    database.getReference().child("chats").child(recieverRoom).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    });
            }
        });



    }

}