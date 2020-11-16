package com.group21.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapt extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messageList;

    static class ViewHolder0 extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;
        public ViewHolder0(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.type0_title);
            authorView = itemView.findViewById(R.id.type0_author);
            publishTimeView = itemView.findViewById(R.id.type0_publishTime);
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;
        public ViewHolder1(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.type1_image);
            titleView = itemView.findViewById(R.id.type1_title);
            authorView = itemView.findViewById(R.id.type1_author);
            publishTimeView = itemView.findViewById(R.id.type1_publishTime);
        }
    }

    static class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;
        public ViewHolder2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.type2_image);
            titleView = itemView.findViewById(R.id.type2_title);
            authorView = itemView.findViewById(R.id.type2_author);
            publishTimeView = itemView.findViewById(R.id.type2_publishTime);
        }
    }

    static class ViewHolder3 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;
        public ViewHolder3(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.type3_image);
            titleView = itemView.findViewById(R.id.type3_title);
            authorView = itemView.findViewById(R.id.type3_author);
            publishTimeView = itemView.findViewById(R.id.type3_publishTime);
        }
    }
    static class ViewHolder4 extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        TextView titleView;
        TextView authorView;
        TextView publishTimeView;
        public ViewHolder4(View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.type4_image_01);
            imageView2 = itemView.findViewById(R.id.type4_image_02);
            imageView3 = itemView.findViewById(R.id.type4_image_03);
            imageView4 = itemView.findViewById(R.id.type4_image_04);
            titleView = itemView.findViewById(R.id.type4_title);
            authorView = itemView.findViewById(R.id.type4_author);
            publishTimeView = itemView.findViewById(R.id.type4_publishTime);
        }
    }


    public MessageAdapt(List<Message> messageList) {
        this.messageList = messageList;
    }

    @Override
    public int getItemViewType(int position) {
        return messageList.get(position).getType();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type0, parent, false);
                return new ViewHolder0(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type1, parent, false);
                return new ViewHolder1(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type2, parent, false);
                return new ViewHolder2(view);
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type3, parent, false);
                return new ViewHolder3(view);
            case 4:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.type4, parent, false);
                return new ViewHolder4(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder instanceof ViewHolder0) {
            ViewHolder0 holder0 = (ViewHolder0) holder;
            holder0.titleView.setText(message.getTitle());
            holder0.authorView.setText(message.getAuthor());
            holder0.publishTimeView.setText(message.getPublishTime());
        } else if (holder instanceof ViewHolder1) {
            ViewHolder1 holder1 = (ViewHolder1) holder;
            holder1.titleView.setText(message.getTitle());
            holder1.authorView.setText(message.getAuthor());
            holder1.publishTimeView.setText(message.getPublishTime());
            holder1.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder2) {
            ViewHolder2 holder2 = (ViewHolder2) holder;
            holder2.titleView.setText(message.getTitle());
            holder2.authorView.setText(message.getAuthor());
            holder2.publishTimeView.setText(message.getPublishTime());
            holder2.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder3) {
            ViewHolder3 holder3 = (ViewHolder3) holder;
            holder3.titleView.setText(message.getTitle());
            holder3.authorView.setText(message.getAuthor());
            holder3.publishTimeView.setText(message.getPublishTime());
            holder3.imageView.setImageResource(message.getCoverId());
        } else if (holder instanceof ViewHolder4) {
            ViewHolder4 holder4 = (ViewHolder4) holder;
            holder4.titleView.setText(message.getTitle());
            holder4.authorView.setText(message.getAuthor());
            holder4.publishTimeView.setText(message.getPublishTime());
            int[] covers = message.getCovers();
            holder4.imageView1.setImageResource(covers[0]);
            holder4.imageView2.setImageResource(covers[1]);
            holder4.imageView3.setImageResource(covers[2]);
            holder4.imageView4.setImageResource(covers[3]);
        }
}

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
