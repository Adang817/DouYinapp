package com.example.douyinapp.liaotiandemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.douyinapp.R;

import java.util.List;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    List<Msg> msgList;

    public MsgAdapter(List msgList) {
        this.msgList = msgList;
    }

   static class ViewHolder extends RecyclerView.ViewHolder {
       LinearLayout leftLayout;
       LinearLayout rightLayout;
       TextView leftMsg;
       TextView rightMsg;

        public ViewHolder(View view) {
            super(view);
            leftLayout = view.findViewById(R.id.left_layout);
            rightLayout = view.findViewById(R.id.right_layout);
            leftMsg = view.findViewById(R.id.leftMsg);
            rightMsg = view.findViewById(R.id.rightmsg);
        }
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
//        if(viewType==Msg.TYPE_RECEIVED){
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_left_item, parent, false);
//        }else{
//            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_right_item, parent, false);
//        }
//        return new ViewHolder(view);

        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_left_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);

        if (msg.getType() == Msg.TYPE_RECEIVED) {
            holder.rightLayout.setVisibility(View.GONE);
            holder.leftMsg.setText(msg.getContent());
        } else {
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightMsg.setText(msg.getContent());
        }

    }

    @Override
    public int getItemViewType(int position) {
        Msg msg=msgList.get(position);
        return msg.getType();
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }

}
