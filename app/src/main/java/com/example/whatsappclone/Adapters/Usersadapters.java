package com.example.whatsappclone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.Models.Users;
import com.example.whatsappclone.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Usersadapters extends RecyclerView.Adapter<Usersadapters.ViewAdapter> {
    ArrayList<Users> list;
    Context context;

    public Usersadapters(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.circular_image_view,parent,false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
    Users users = list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.facebook).into(holder.image);
        holder.userName.setText(users.getUserName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewAdapter extends RecyclerView.ViewHolder{
        ImageView image;
        TextView userName, lastMessage;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.Username);
            lastMessage = itemView.findViewById(R.id.lastMessage);





        }


    }
}
