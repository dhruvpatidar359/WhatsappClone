package com.example.whatsappclone.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.InsideChatActivity;
import com.example.whatsappclone.Models.MessageModels;
import com.example.whatsappclone.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdap extends RecyclerView.Adapter {



    public void setMessageAdaps(ArrayList<MessageModels> messageAdaps) {
        this.messageAdaps = messageAdaps;
    }

    ArrayList<MessageModels> messageAdaps;
    Context context;

    public MessageAdap(ArrayList<MessageModels> messageAdaps, Context context) {
        this.messageAdaps = messageAdaps;
        this.context = context;
    }

    int SENDER_VIEW_TYPE  = 1;
    int RECIEVER_VIEW_TYPE = 2;




    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(messageAdaps.get(position).getUid().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECIEVER_VIEW_TYPE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);

        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

MessageModels messageModels =messageAdaps.get(position);

if(holder.getClass() == SenderViewHolder.class)
    ((SenderViewHolder) holder).senderMsg.setText(messageModels.getMessage());

else{
    ((ReceiverViewHolder)holder).receiverMsg.setText(messageModels.getMessage());
}


    }

    @Override
    public int getItemCount() {
        return messageAdaps.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView receiverMsg , receiverTime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg= itemView.findViewById(R.id.reciever_text);
            receiverTime= itemView.findViewById(R.id.reciever_time);
        }
    }

    public class SenderViewHolder extends  RecyclerView.ViewHolder {

        TextView senderMsg , senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.sender_Text);
            senderTime = itemView.findViewById(R.id.sender_Time);

        }
    }
}
