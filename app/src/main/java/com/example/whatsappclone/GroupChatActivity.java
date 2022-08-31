package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.whatsappclone.Adapters.MessageAdap;
import com.example.whatsappclone.Models.MessageModels;
import com.example.whatsappclone.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChatActivity extends AppCompatActivity {
ActivityGroupChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessageModels> arrayList = new ArrayList<>();
        final MessageAdap messageAdap = new MessageAdap(arrayList,this);
        binding.insideChatsRecycler.setAdapter(messageAdap);

        final String senderID = FirebaseAuth.getInstance().getUid();
        binding.Usernameinsidechats.setText("Bhai-Bhai");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.insideChatsRecycler.setLayoutManager(linearLayoutManager);

        database.getReference().child("groupchat")
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                            arrayList.clear();
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                MessageModels models = dataSnapshot.getValue(MessageModels.class);
                                arrayList.add(models);

                            }
                            messageAdap.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });


        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String message = binding.Message.getText().toString();
                final MessageModels models = new MessageModels(senderID,message);
                binding.Message.setText("");
                models.setTimestamp(new Date().getTime());

                database.getReference().child("groupchat")
                        .push()
                        .setValue(models)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });
            }
        });






    }
}