package com.example.whatsappclone.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MessageAdap extends RecyclerView.Adapter {



    public void setMessageAdaps(ArrayList<MessageModels> messageAdaps) {
        this.messageAdaps = messageAdaps;
    }

    ArrayList<MessageModels> messageAdaps;

    public MessageAdap(ArrayList<MessageModels> messageAdaps, Context context, String recId) {
        this.messageAdaps = messageAdaps;
        this.context = context;
        this.recId = recId;
    }

    Context context;
    String recId;

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

holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
    @Override
    public boolean onLongClick(View view) {
        new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Do you want to delete the message")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase firebaseDatabase  = FirebaseDatabase.getInstance();
                        String sender = FirebaseAuth.getInstance().getUid() + recId;
                        firebaseDatabase.getReference().child("chats").child(sender)
                                .child(messageModels.getMessageID()).setValue(null);
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();

        return false;
    }
});

if(holder.getClass() == SenderViewHolder.class){
    ((SenderViewHolder) holder).senderMsg.setText(messageModels.getMessage());
        ((SenderViewHolder) holder).senderTime.setText(formatDate(messageModels.getTimestamp()));}

else{
    ((ReceiverViewHolder)holder).receiverMsg.setText(messageModels.getMessage());
    ((ReceiverViewHolder) holder).receiverTime.setText(formatDate(messageModels.getTimestamp()));
}


    }
    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
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
