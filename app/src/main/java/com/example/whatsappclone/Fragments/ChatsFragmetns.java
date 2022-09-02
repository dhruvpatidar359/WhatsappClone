package com.example.whatsappclone.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.whatsappclone.Adapters.Usersadapters;
import com.example.whatsappclone.Models.Users;
import com.example.whatsappclone.R;

import com.example.whatsappclone.databinding.FragmentChatsFragmetnsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;


public class ChatsFragmetns extends Fragment {


    public ChatsFragmetns() {
        // Required empty public constructor
    }

    FragmentChatsFragmetnsBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         binding = FragmentChatsFragmetnsBinding.inflate(inflater, container, false);
        Usersadapters usersadapters = new Usersadapters(list,getContext());
        binding.recyclerView.setAdapter(usersadapters);

        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext());
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot datasnapshot: snapshot.getChildren())
                {
                    Users users = datasnapshot.getValue(Users.class);
                    users.setUserID(datasnapshot.getKey());
                    if(!users.getUserID().equals(FirebaseAuth.getInstance().getUid()))
                    list.add(users);
                }
                usersadapters.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







         return binding.getRoot();
    }
}